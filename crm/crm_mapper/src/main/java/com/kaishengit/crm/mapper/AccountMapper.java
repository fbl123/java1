package com.kaishengit.crm.mapper;

import com.kaishengit.crm.entity.Account;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/7/18.
 */
public interface AccountMapper {


    List<Account> findAll();

    void save(Account account);

    void delect(@Param("id")Integer id);

    Long count();

    Long countByDeptId(@Param("id")String id);

    Account findByModile(String mobile);

    List<Account> findByDeptId(@Param("id") String id);

    void update(Account account);
}
