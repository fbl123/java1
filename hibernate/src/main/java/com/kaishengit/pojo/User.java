package com.kaishengit.pojo;

import java.util.Set;

public class User {


    private Integer id;
    private String name;
    private Set<Boos> boosSet;

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

    public Set<Boos> getBoosSet() {
        return boosSet;
    }

    public void setBoosSet(Set<Boos> boosSet) {
        this.boosSet = boosSet;
    }
}
