package com.kaishengit.dao;

import com.kaishengit.entity.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
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

    public List<Student> findAll(){
        String sql="select * from student";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Student>(Student.class));
    }

    public Student select(int id){
        String sql="select * from student where id=?";
        return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(Student.class),id);

    }
    public int count(){
        String sql="select count(*) from student";
        return jdbcTemplate.queryForObject(sql,Long.class).intValue();
    }
    public  void inster(Student stu){
        String sql="insert into student(name,password,class_id)values(?,?,?)";
        jdbcTemplate.update(sql,stu.getName(),stu.getPassword(),stu.getClass_id());
    }

    public Student select(String name){
        String sql="select * from student where name=?";
        return jdbcTemplate.queryForObject(sql, new RowMapper<Student>() {
            @Override
            public Student mapRow(ResultSet resultSet, int i) throws SQLException {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setPassword(resultSet.getString("password"));
                student.setClass_id(resultSet.getInt("class_id"));

                return student;
            }
        }, name);

    }



}
