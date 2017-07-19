package com.kaishengit.dto;

/**
 * Ajax返回对象
 * Created by Administrator on 2017/7/17.
 */
public class Result {
    private static final String SUCCESS="success";
    private static final String ERROR="error";

    private String state;
    private String message;
    private Object data;
    public static Result success(String message,Object data){
        return new Result(SUCCESS,message,data);
    }
    public static Result success(){return new Result(SUCCESS);}
    public static Result success(Object data){
        Result res=new Result(SUCCESS);
        res.setData(data);
        return res;
    }
    public static Result error(String message){
        Result res=new Result(ERROR,message);
        return res;
    }



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
    //返回数据和客户访问路径
    public Result(String state, String message, Object data) {
        this.state = state;
        this.message = message;
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
