package com.kaishengit;

import com.kaishengit.entity.CGLib;
import com.kaishengit.entity.MyInteface;
import com.kaishengit.entity.Perpor;
import com.kaishengit.entity.PerporInvoke;
import org.springframework.cglib.proxy.Enhancer;

import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2017/7/7.
 */
public class test {

    public static void main(String[] args) {
        /**
         * JDK动态代理
         * 1.创建被代理对象
         * 2.创建代理对象
         * 3.使用Proxy.newProxyInstance()
         * 4.调用方法
         */
     /*   Perpor per=new Perpor();
        PerporInvoke perporInvoke=new PerporInvoke(per);
        MyInteface myInteface=(MyInteface) Proxy.newProxyInstance(per.getClass().getClassLoader(),
                per.getClass().getInterfaces(),perporInvoke);
        myInteface.sayName("jick");*/

        /**
         * CGLib动态代理
         */
     /*   Enhancer enhancer=new Enhancer();
        //设置被代理的类
        enhancer.setSuperclass(Perpor.class);
        //设置代理对象
        enhancer.setCallback(new CGLib());
        //创建代理对象
        Perpor per=(Perpor)enhancer.create();
        //调用方法
        per.sayName("kobe");*/

        /**
         * AOP
         */
        Perpor per=new Perpor();
//        per.sayName("jick");
       int a= (int) per.ha("jick");
        System.out.println(a);
    }
}
