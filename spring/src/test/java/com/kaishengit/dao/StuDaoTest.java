package com.kaishengit.dao;

import com.kaishengit.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2017/7/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class StuDaoTest {
    @Autowired
    StuDao stuDao;
    @Test
    public void select() throws Exception {
        Student stu= stuDao.select(1);
//        System.out.println(stu);
        System.out.println(stu.getId());


    }

}