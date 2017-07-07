package com.kaishengit.dap;

import com.kaishengit.dao.StuDao;
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
        StuDao stuDao=(StuDao)applicationContext.getBean("sd");
        stuDao.say();
    }
}
