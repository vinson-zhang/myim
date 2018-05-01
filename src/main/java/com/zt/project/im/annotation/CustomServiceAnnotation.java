package com.zt.project.im.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author：ZhangTao
 * @Description:
 * @Date: Created in 21:17 2018/4/13
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CustomServiceAnnotation {
    int type();
}
