package com.kaishengit.entity;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/7/10.
 */
public class PerporInvoke implements InvocationHandler {


    private Object perpor;

    public PerporInvoke(Perpor perpor) {
        this.perpor = perpor;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return
                method.invoke(perpor,args);

    }
}
