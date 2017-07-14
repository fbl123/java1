package com.kaishengit.utils;

import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/7/14.
 */

public class Result {

    private String state;
    private String message;
    private Object data;

    //出现异常时调用此构造
    public Result(String state, String message){
        this.state = state;
        this.message = message;
    }

    //正常返回数据时调用此构造
    public Result(String state, Object data){
        this.state = state;
        this.data = data;
    }

    //正常返回数据时调用此构造
    public Result(String state){
        this.state = state;
    }

    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }

}
