package com.kaishengit.Service;

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
public class DaoServiceTest {
    @Test
    public void findAll() throws Exception {


        List<Student> list=daoService.findAll();
        for(Student stu:list){
            System.out.println(stu);
        }
    }

    @Autowired
    DaoService daoService;
    @Test
    public void save() throws Exception {

        daoService.save();

    }


}