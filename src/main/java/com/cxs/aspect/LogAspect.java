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
 * @Date:2019/3/22 19:49
 * 切面类
 */
@Component
@Aspect
public class LogAspect {

    /**
     * slf4j
     */
    private static Logger log = LoggerFactory.getLogger(LogAspect.class);

    @Around("execution(* com.cxs.service.impl.*.*(..))")
    public Object logPrint(ProceedingJoinPoint point) {
        //该方法属于哪个类型
        Object target = point.getTarget();
        //方法签名
        Signature signature = point.getSignature();
        //方法参数
        Object[] args = point.getArgs();
        log.debug("{" + target.getClass().getName() + " => " + signature.getName() + " => " + args.toString());
        Object result = null;
        try {
            result = point.proceed(args);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        return result;
    }


}
