package com.kaishengit.entity;

public class Student {

	private Integer id;
	private String name;
	private Integer classId;
	private StuClass cl;
	
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", classId=" + classId + ", cl=" + cl + ", password=" + password
				+ "]";
	}
	
	
	
	public StuClass getCl() {
		return cl;
	}
	public void setCl(StuClass cl) {
		this.cl = cl;
	}
	public Integer getClassId() {
		return classId;
	}
	public void setClassId(Integer classId) {
		this.classId = classId;
	}
	private String password;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	
	
	
	
	
}
