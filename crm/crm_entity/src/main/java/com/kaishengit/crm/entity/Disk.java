package com.kaishengit.crm.entity;

import java.util.Date;

public class Disk {
    public static final String TYPE_FILE="file";
    public static final String File_DIR="dir";

    private Integer id;
    private Integer accountId;

    public int getDownLoadCount() {
        return downLoadCount;
    }

    public void setDownLoadCount(int downLoadCount) {
        this.downLoadCount = downLoadCount;
    }

    private int downLoadCount;



    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    private Integer pid;
    private String type;
    private String name;
    private String saveName;
    private Date uploadTime;
    private String password;

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    private String md5;
    private String size;




    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSaveName() {
        return saveName;
    }

    public void setSaveName(String saveName) {
        this.saveName = saveName;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }
}
