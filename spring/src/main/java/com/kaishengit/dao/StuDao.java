package com.kaishengit.dao;

import com.kaishengit.entity.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/7/7.
 */
@Repository
public class StuDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void say(){
        System.out.print("haha");
    }
    public void init(){
        System.out.println("初始化");
    }
    public void destory(){
        System.out.println("销毁");
    }

    public Student select(int id){
        String sql="select * from student where id=?";
        return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(Student.class),id);

    }





}
