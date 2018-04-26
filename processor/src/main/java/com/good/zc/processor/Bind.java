package com.good.zc.processor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Zc on 2018/4/23.
 */

@Retention(RetentionPolicy.CLASS)
@Target(ElementType.FIELD)
public @interface Bind
{
    int value();
}
