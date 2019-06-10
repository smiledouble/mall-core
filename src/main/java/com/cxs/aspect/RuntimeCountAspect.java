package com.cxs.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Author:chenxiaoshuang
 * @Date:2019/3/22 19:57
 */
@Component
@Aspect
public class RuntimeCountAspect {

    private static Logger log = LoggerFactory.getLogger(RuntimeCountAspect.class);

    @Around("execution(* com.cxs.service.impl.*.*(..))")
    public Object countMethodExeTime(ProceedingJoinPoint point) {
        long start = System.currentTimeMillis();
        Object result = null;
        try {
            result = point.proceed(point.getArgs());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        long end = System.currentTimeMillis();
        //方法签名
        Signature signature = point.getSignature();
        log.debug("{" + signature.getName() + "=" + (end - start) + "}");
        return result;

    }


}
