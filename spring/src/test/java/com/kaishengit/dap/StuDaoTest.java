package com.kaishengit.dap;

import com.kaishengit.dao.StuDao;
import com.kaishengit.dao.com.kaishengit.dao.BookDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 * Created by Administrator on 2017/7/7.
 */
public class StuDaoTest {

    @Test
    public void say(){
        /**
         *
         */
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
//        StuDao stuDao=(StuDao)applicationContext.getBean("sd");
        StuDao stuDao=(StuDao)applicationContext.getBean("dao");
        stuDao.say();
    }

    @Test
    public void test(){
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
        BookDao book=(BookDao)applicationContext.getBean("book");
        System.out.println(book);
    }
}
