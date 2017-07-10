package com.kaishengit;

import com.kaishengit.entity.MyInteface;
import com.kaishengit.entity.Perpor;
import com.kaishengit.entity.PerporInvoke;

import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2017/7/7.
 */
public class test {

    public static void main(String[] args) {
        Perpor per=new Perpor();
        PerporInvoke perporInvoke=new PerporInvoke(per);
        MyInteface myInteface=(MyInteface) Proxy.newProxyInstance(per.getClass().getClassLoader(),
                per.getClass().getInterfaces(),perporInvoke);
        myInteface.sayName("jick");
    }
}
