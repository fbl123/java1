package com.kaishengit.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/my")
public class MyController {


    @GetMapping
    public ModelAndView ha(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("messge","haah");
        modelAndView.setViewName("hh");
        return modelAndView;
    }
}
