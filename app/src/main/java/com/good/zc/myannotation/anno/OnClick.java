package com.good.zc.myannotation.anno;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Zc on 2018/4/23.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)//目标：方法
@EventBase(listenerType = View.OnClickListener.class,listenerSetter = "setOnClickListener",methodName = "onClick")
public @interface OnClick {
    int[] value();
}
