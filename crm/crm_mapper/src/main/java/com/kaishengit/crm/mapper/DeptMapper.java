package com.kaishengit.crm.mapper;

import com.kaishengit.crm.entity.Dept;

import java.util.List;

/**
 * Created by Administrator on 2017/7/17.
 */
public interface DeptMapper {


    List<Dept> findAll();

    void save(Dept dept);
}
