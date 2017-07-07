package com.kaishengit.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.kaishengit.entity.Student;

public interface StudentMapper {
	@Select("select*from student where id=#{id}")
//	@Options(useGeneratedKeys=true,keyProperty="id")
	Student selectStudent(int id);
//	public Student findById(Integer id);
	public List<Student> findAll();
	public void save(Student stu);
	public List<Student> findClass();
	public Student findByNameAndClassId(@Param("name") String name,@Param("pwd") String passworrd);
	public void saveList(List<Student> list);
	public Student findBy(Map map);

}
