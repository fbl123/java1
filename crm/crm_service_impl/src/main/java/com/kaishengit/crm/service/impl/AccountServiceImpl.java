package com.kaishengit.crm.service.impl;

import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.AccountDept;
import com.kaishengit.crm.mapper.AccountDeptMapper;
import com.kaishengit.crm.mapper.AccountMapper;
import com.kaishengit.crm.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by Administrator on 2017/7/18.
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private AccountDeptMapper accountDeptMapper;


    @Override
    @Transactional
    public void save(Account account,Integer[] integers) {
        account.setCreatTime(new Date());
        accountMapper.save(account);
        for(Integer dd:integers){
            AccountDept accountDept=new AccountDept();
            accountDept.setDeptId(dd);
            accountDept.setAccountId(account.getId());
            accountDeptMapper.save(accountDept);
        }

    }
}
