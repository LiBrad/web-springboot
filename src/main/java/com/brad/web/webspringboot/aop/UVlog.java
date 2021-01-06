package com.brad.web.webspringboot.aop;

import java.lang.annotation.*;

/**
 * @version 1.0
 * @author： 李逸野
 * @date： 2020-11-10
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface UVlog {
    String type() default "";
}