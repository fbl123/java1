package com.kaishengit.dap;

import com.kaishengit.dao.ClassDao;
import com.kaishengit.dao.StuDao;
import com.kaishengit.dao.com.kaishengit.dao.BookDao;
import com.kaishengit.entity.MyInteface;
import com.kaishengit.entity.Perpor;
import com.kaishengit.entity.PerporInvoke;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2017/7/7.
 */
public class StuDaoTest {

    ApplicationContext applicationContext;
    @Before
    public void before(){
         applicationContext= new ClassPathXmlApplicationContext("applicationContext.xml");
    }
    @Test
    public void say(){
        /**
         *
         */
//        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
//        StuDao stuDao=(StuDao)applicationContext.getBean("sd");
        StuDao stuDao=(StuDao)applicationContext.getBean("dao");
        stuDao.say();

    }

    @Test
    public void test(){
//        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
        BookDao book=(BookDao)applicationContext.getBean("book");
        System.out.println(book);
    }
    @Test
    public void classtest(){
        ClassDao dao= (ClassDao) applicationContext.getBean("class");
        System.out.println(dao.name);


    }
    @Test
    public void target(){
        Perpor per=new Perpor();
        PerporInvoke perporInvoke=new PerporInvoke(per);
        MyInteface myInteface=(MyInteface)Proxy.newProxyInstance(per.getClass().getClassLoader(),
                per.getClass().getInterfaces(),perporInvoke);
        myInteface.sayName("jick");


    }

}
