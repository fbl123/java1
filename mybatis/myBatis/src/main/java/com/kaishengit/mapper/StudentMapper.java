package com.kaishengit.mapper;

import java.util.List;

import com.kaishengit.entity.Student;

public interface StudentMapper {

	public Student findById(Integer id);
	public List<Student> findAll();
	public void save(Student stu);
	public List<Student> findClass();
	
}
