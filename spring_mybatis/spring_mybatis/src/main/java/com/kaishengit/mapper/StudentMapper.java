package com.kaishengit.mapper;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.kaishengit.entity.Student;

public interface StudentMapper {

	public void save(Student stu);
	
	public PageInfo<Student> findByPage();
	public List<Student> findAll();
}
