package com.cxs.cache;

import com.cxs.anno.CleanCache;
import com.cxs.anno.EnableCache;
import com.cxs.redis.RedisClient;
import com.cxs.utils.SerializationUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @Author:chenxiaoshuang
 * @Date:2019/3/27 16:35
 */
@Component
@Aspect
public class CacheAspect {

    @Autowired
    private RedisClient redis;

    /**
     * 缓存添加Aop
     *
     * @param point
     * @return
     */
    @Around("@annotation(com.cxs.anno.EnableCache)")
    public Object cacheAop(ProceedingJoinPoint point) {
        //通过下面的反射拼接等操作 或得到隔离的key
        byte[] key = getKey(point);
        if (redis.isExist(key)) {
            //如果缓存存在key 就直接返回
            byte[] value = redis.get(key);
            //序列化成对象 然后返回
            return SerializationUtil.deSerialization(value);
        }
        //如果不存在 就查询出来放到缓存中 再返回
        Object result = null;
        try {
            result = point.proceed(point.getArgs());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        if (result != null) {
            //放到缓存中
            redis.set(key, SerializationUtil.inSerialization(result));
        }
        return result;
    }


    /**
     * 清空缓存的方法 用于解决缓存脏读
     *
     * @param point
     * @return
     */
    @Around("@annotation(com.cxs.anno.CleanCache)")
    public Object cleanCache(ProceedingJoinPoint point) {
        byte[] key = getCleanKey(point);
        if (redis.isExist(key)) {
            //如果存在key 就删掉
            redis.del(key);
        }
        Object result = null;
        try {
            result = point.proceed(point.getArgs());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return result;
    }

    /**
     * 获取删除的key
     *
     * @param point
     * @return
     */
    private byte[] getCleanKey(ProceedingJoinPoint point) {
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        //接口的方法名
        Method method = methodSignature.getMethod();
        Method realMethod = null;
        try {
            //得到真实的方法名
            realMethod = point.getTarget().getClass().getMethod(method.getName(), method.getParameterTypes());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        CleanCache annotation = realMethod.getAnnotation(CleanCache.class);
        String namespace = annotation.namespace();
        String methodName = annotation.method();
        String label = annotation.label();
        if (!"".equals(label) && label.contains("#") && label.contains(".")) {
            //如果label存在#和. 才往下走
            label = getLabel(label, point);
        } else if (label.contains("#") && !label.contains(".")) {
            String paramIndex = label.split("#")[1];
            label = point.getArgs()[Integer.valueOf(paramIndex)].toString();
        }
        return (namespace + methodName + label).getBytes();
    }

    /**
     * 解析EL表达式
     *
     * @param
     * @param
     * @return
     */
    private String getLabel(String label, ProceedingJoinPoint point) {
        Object paramValue = null;
        //#0.id
        String param = label.split("#")[1];
        //0 id
        String[] paramIndex = param.split("\\.");
        //获取第一个参数对象
        Object argObject = point.getArgs()[Integer.valueOf(paramIndex[0])];
        try {
            //获得属性 id
            Field field = argObject.getClass().getDeclaredField(paramIndex[1]);
            //暴力访问
            field.setAccessible(true);
            //最终获得参数的值
            paramValue = field.get(argObject);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return paramValue.toString();
    }


    /**
     * 获取key
     *
     * @param point
     * @return
     */
    private byte[] getKey(ProceedingJoinPoint point) {
        String key = null;
        //获取方法签名 强转成方法
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        //这个方法是接口里面的方法，需要获取真是方法
        Method method = methodSignature.getMethod();
        Method realMethod = null;
        try {
            //真实的方法名
            realMethod = point.getTarget().getClass().getDeclaredMethod(method.getName(), method.getParameterTypes());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        //得到方法上面的注解
        EnableCache cache = realMethod.getAnnotation(EnableCache.class);
        //实现类中的不同方法的缓存隔离
        String namespace = cache.namespace();

        key = namespace + realMethod.getName();

        //注解的参数
        String label = cache.label();
        //获取方法的参数
        Object[] args = point.getArgs();
        if (!"".equals(label) && label.contains("#")) {
            //获得第一个参数的索引
            String paramIndex = label.split("#")[1];
            key += args[Integer.parseInt(paramIndex)].toString();
        } else {
            key += label;
        }
        return key.getBytes();
    }


}
