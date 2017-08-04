package com.kaishengit.util;

public class Condition {

    //查询属性
    private String parameName;
    //值
    private Object value;
    //比较方式
    private String Type;


    public Condition(String parameName, Object value, String type) {

        this.parameName = parameName;
        this.value = value;
        Type = type;
    }

    public String getParameName() {
        return parameName;
    }

    public void setParameName(String parameName) {
        this.parameName = parameName;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}

