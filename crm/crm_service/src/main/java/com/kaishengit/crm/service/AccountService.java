package com.kaishengit.crm.service;

import com.kaishengit.crm.entity.Account;
import java.util.List;

/**
 * Created by Administrator on 2017/7/18.
 */
public interface AccountService {
    void save(Account account,Integer[] integers);

    void delect(Integer id);

    List<Account> findAll();

    Long count();

    Long countByDeptId(Integer id);

    Account findByModile(String modile,String password);

    List<Account> findByDeptId(Integer id);
}
