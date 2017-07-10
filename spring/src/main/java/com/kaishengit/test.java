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
         */
     /*   Perpor per=new Perpor();
        PerporInvoke perporInvoke=new PerporInvoke(per);
        MyInteface myInteface=(MyInteface) Proxy.newProxyInstance(per.getClass().getClassLoader(),
                per.getClass().getInterfaces(),perporInvoke);
        myInteface.sayName("jick");*/

        /**
         * CGLib动态代理
         */
        Enhancer enhancer=new Enhancer();
        enhancer.setSuperclass(Perpor.class);//设置被代理的类
        enhancer.setCallback(new CGLib());  //设置代理对象
        Perpor per=(Perpor)enhancer.create(); //创建代理对象
        per.sayName("kobe");

    }
}
