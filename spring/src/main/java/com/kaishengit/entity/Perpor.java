package com.kaishengit.entity;

import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/7/10.
 */
@Service("pp")
//public  class Perpor implements MyInteface{

public class Perpor{

//    @Override
//    public void sayName(String name) {
//        System.out.println(name);
//
//    }
    public void sayName(String name){

        System.out.println("name--->"+name);
    }
    public Object ha(String name){
        return 100;
    }
}
