package com.kaishengit.dao;

import com.kaishengit.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2017/7/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class StuDaoTest {
    @Test
    public void inster() throws Exception {
        Student stu=new Student();
        stu.setClass_id(1);
        stu.setPassword("321");
        stu.setName("jiye");
        stuDao.inster(stu);

    }

    @Test
    public void findAll() throws Exception {
        List<Student> list=stuDao.findAll();
        for (Student stu:list){
            System.out.println(stu);
        }

    }

    @Autowired
    StuDao stuDao;
    @Test
    public void count() throws Exception {
        System.out.println(stuDao.count());
    }


    @Test
    public void select() throws Exception {
        Student stu= stuDao.select(1);
//        System.out.println(stu);
        System.out.println(stu.getId());

    }


}