package com.kaishengit.crm.mapper;

import com.kaishengit.crm.entity.AccountDept;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Administrator on 2017/7/18.
 */
public interface AccountDeptMapper {

    void save(AccountDept accountDept);

    void delect(@Param("id") Integer id);
}
