package com.good.zc.myannotation.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Zc on 2018/4/23.
 */
@Retention(RetentionPolicy.RUNTIME)//运行时注解
@Target(ElementType.TYPE) //接口、类、枚举、注解  这里是类
public @interface ContentView {
    int value();//需要传入界面布局id
}
