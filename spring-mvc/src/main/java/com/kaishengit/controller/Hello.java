package com.kaishengit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Administrator on 2017/7/13.
 */

@Controller
@RequestMapping("/hello")
public class Hello {

    @RequestMapping(method = RequestMethod.GET)
    public String say(){
        return "hello";
    }

    @RequestMapping("/list/{id}")
    public String hhh(@PathVariable String id){
        System.out.println(id);
        return "list";
    }
}


