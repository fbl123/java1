package com.kaishengit.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.PageInfo;
import com.kaishengit.entity.Student;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring.xml")
public class StudentServiceTest {
	@Autowired
	private StudentService studentService;
	@Test
	public void save(){
		
		studentService.save();	
		
	}
	@Test
	public void findByPage(){
		PageInfo<Student> page=studentService.findByPage(2, 10);
		for(Student stu:page.getList()){
			System.out.println(stu);
			
		}
		
	}
	
	
}
