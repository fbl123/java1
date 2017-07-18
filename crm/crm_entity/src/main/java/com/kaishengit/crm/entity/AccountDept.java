package com.kaishengit.crm.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/18.
 */
public class AccountDept implements Serializable {
    private static final long serialVersionUID = 1L;


    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    private Integer deptId;

    private Integer accountId;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }



    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }
}
