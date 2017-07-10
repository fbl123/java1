package com.kaishengit.entity;

/**
 * Created by Administrator on 2017/7/10.
 */
public  class Perpor implements MyInteface{



    @Override
    public void sayName(String name) {
        System.out.println(name);

    }

    public Object ha(String name){
        return 100;
    }
}
