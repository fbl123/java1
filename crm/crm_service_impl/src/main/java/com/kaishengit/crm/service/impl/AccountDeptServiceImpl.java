package com.kaishengit.crm.service.impl;

import com.kaishengit.crm.entity.AccountDept;
import com.kaishengit.crm.mapper.AccountDeptMapper;
import com.kaishengit.crm.mapper.AccountMapper;
import com.kaishengit.crm.service.AccountDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/7/18.
 */
@Service
public class AccountDeptServiceImpl implements AccountDeptService {
    @Autowired
    private AccountDeptMapper accountDeptMapper;
    @Override
    public void save(AccountDept accountDept) {
        accountDeptMapper.save(accountDept);
    }
}
