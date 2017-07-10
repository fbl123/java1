package com.kaishengit.entity;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/7/10.
 */
public class CGLib implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("开始");
        Object obj=methodProxy.invokeSuper(o,objects);
        System.out.println("结束");
        return null;
    }
}
