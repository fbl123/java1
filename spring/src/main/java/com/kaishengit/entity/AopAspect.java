package com.kaishengit.entity;

import org.aspectj.lang.annotation.*;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/7/10.
 * 使用注解的方式交给Spring管理
 */
@Component
@Aspect
public class AopAspect {
    @Pointcut("execution(* com.kaishengit..*.*(..))")
    public void aspect(){}
    //后置通知
//    @AfterReturning(pointcut="aspect()",returning="name")
    public void afterAdvice(){
        System.out.println("后置通知");
    }
    //异常通知
    @AfterThrowing(pointcut = "aspect()",throwing = "e")
    public void afterThrow(Exception e){
        System.out.println(e.getMessage()+"异常通知");
    }
    //前置通知
//    @Before("aspect()")
    public void before() {
        System.out.println("前置通知");
    }
    //最终通知
    @After("aspect()")
    public void after(){
        System.out.println("最终通知");
    }

}
