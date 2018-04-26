package com.good.zc.myannotation.buildanno;

/**
 * Created by Zc on 2018/4/23.
 */

public interface ViewInject<T>
{
    void inject(T t, Object source);
}
