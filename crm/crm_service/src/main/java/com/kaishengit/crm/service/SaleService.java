package com.kaishengit.crm.service;

import com.github.pagehelper.PageInfo;
import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.Sale;
import com.kaishengit.exception.ServiceException;

import java.util.List;
import java.util.Map;

public interface SaleService {


    public List<String> getProgressList();

    void save(Sale sale) throws ServiceException;


    PageInfo<Sale> findMySale(Account account, Map<String,Object> map);
}
