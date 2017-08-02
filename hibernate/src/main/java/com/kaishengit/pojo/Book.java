package com.kaishengit.pojo;

import java.io.Serializable;

public class Book implements Serializable{
	
	
	
	private Integer id;
	private String name;
    private Aconter aconter;

	@Override
	public String toString() {
		return "Book{" +
				"id=" + id +
				", name='" + name + '\'' +
				", aconter=" + aconter +
				'}';
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

	public Aconter getAconter() {
		return aconter;
	}

	public void setAconter(Aconter aconter) {
		this.aconter = aconter;
	}
}
