package com.good.zc.myannotation.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Zc on 2018/4/23.
 */
@Target(ElementType.ANNOTATION_TYPE)//目标：自定义注解
@Retention(RetentionPolicy.RUNTIME)
public @interface EventBase {
    Class<?> listenerType();

    String listenerSetter();

    String methodName();
}
