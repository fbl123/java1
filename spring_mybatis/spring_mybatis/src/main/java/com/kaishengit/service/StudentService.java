package com.kaishengit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaishengit.entity.Student;
import com.kaishengit.mapper.StudentMapper;

@Service
public class StudentService {
	@Autowired
	private StudentMapper studentMapper;
	@Transactional
	public void save(){
		Student stu=new Student();
		stu.setName("liken");
		stu.setPassword("1231");
		stu.setClassId(1);
		studentMapper.save(stu);
		if(true){
			throw new RuntimeException("heiehi");
		}
		studentMapper.save(stu);
	}
	
	public PageInfo<Student> findByPage(int pageNo,int pageSize){
		PageHelper.startPage(pageNo,pageSize);
		List<Student> list=studentMapper.findAll();
		return new PageInfo<Student>(list);
	}
	
	
}
