package com.kaishengit.entity;

public class Student {

	private Integer id;
	private String name;
	private String password;
	private Integer classId;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", password=" + password + ", classId=" + classId + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getClassId() {
		return classId;
	}
	public void setClassId(Integer classId) {
		this.classId = classId;
	}
	
	
}
