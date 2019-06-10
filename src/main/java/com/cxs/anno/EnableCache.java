package com.cxs.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author:chenxiaoshuang
 * @Date:2019/3/27 16:10
 * 设计缓存的注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EnableCache {

    String namespace() default "";

    String label() default "";
}
