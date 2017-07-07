package com.kaishengit.entity;


import java.io.Serializable;

public class Student implements Serializable {
    private static final long serialVersionUID = -6127347815262738854L;

    /**
	 * 
	 */

	/**
	 * 
	 */
	private Integer id;
	private String name;
	private String password;
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
