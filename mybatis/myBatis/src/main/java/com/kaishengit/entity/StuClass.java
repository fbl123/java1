package com.kaishengit.entity;

public class StuClass {

	private Integer id;
	private String className;
	public Integer getId() {
		return id;
	}
	@Override
	public String toString() {
		return "StuClass [id=" + id + ", className=" + className + "]";
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	
	
	
	
}
