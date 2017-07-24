package com.kaishengit.crm.entity;


import java.io.Serializable;
import java.util.Date;

/**
 * 销售机会
 */
public class Sale implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    //机会名称
    private String saleName;
    //描述
    private String description;
    //进度
    private String progress;
    //机会价值
    private double worth;
    //员工ID
    private Integer accountId;
    //客户ID
    private Integer customerId;
    //创建时间
    private Date createTime;
    //最后跟进时间
    private Date lastTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    //客户
    private Customer customer;
    //员工
    private Account account;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSaleName() {
        return saleName;
    }

    public void setSaleName(String saleName) {
        this.saleName = saleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public double getWorth() {
        return worth;
    }

    public void setWorth(double worth) {
        this.worth = worth;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }
}
