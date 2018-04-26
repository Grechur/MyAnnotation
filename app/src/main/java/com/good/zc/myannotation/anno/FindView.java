package com.good.zc.myannotation.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Zc on 2018/4/23.
 */
@Retention(RetentionPolicy.RUNTIME)//运行时注解
@Target(ElementType.FIELD)//目标：属性
public @interface FindView {
    int value();//指定控件id
}
