package com.kaishengit.crm.service;

import com.kaishengit.crm.entity.Account;
import com.kaishengit.exception.ServiceException;

import java.util.List;

/**
 * Created by Administrator on 2017/7/18.
 */
public interface AccountService {
    void save(Account account,Integer[] integers);

    void delect(Integer id);

    List<Account> findAll();

    Long count();

    Long countByDeptId(String id);

    Account findByModile(String modile,String password) throws ServiceException;

    List<Account> findByDeptId(String id);

    void update(String oldPassword, Account acc, Account session) throws ServiceException;
}
