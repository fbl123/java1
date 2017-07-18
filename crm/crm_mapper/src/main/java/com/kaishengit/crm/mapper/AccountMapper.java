package com.kaishengit.crm.mapper;

import com.kaishengit.crm.entity.Account;

import java.util.List;

/**
 * Created by Administrator on 2017/7/18.
 */
public interface AccountMapper {


    List<Account> findAll();

    void save(Account account);

}
