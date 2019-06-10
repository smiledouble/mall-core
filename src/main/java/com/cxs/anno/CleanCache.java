package com.cxs.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author:chenxiaoshuang
 * @Date:2019/3/27 21:08
 * 清空缓存的注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CleanCache {
    /**
     * 命名空间
     *
     * @return
     */
    String namespace() default "";

    /**
     * 方法名
     *
     * @return
     */
    String method() default "";

    /**
     * 参数
     *
     * @return
     */
    String label() default "";

}
