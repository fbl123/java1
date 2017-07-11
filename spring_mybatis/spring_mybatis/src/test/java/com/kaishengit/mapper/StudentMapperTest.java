package com.kaishengit.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kaishengit.entity.Student;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring.xml")
public class StudentMapperTest {
	@Autowired
	private StudentMapper studentMapper;
	
	@Test
	public void save(){
		Student stu=new Student();
		stu.setName("yuran");
		stu.setPassword("231");
		stu.setClassId(2);
		studentMapper.save(stu);
		
		
	}
	
	
	
}
