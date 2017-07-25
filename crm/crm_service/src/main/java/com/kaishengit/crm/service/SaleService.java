package com.kaishengit.crm.service;

import com.github.pagehelper.PageInfo;
import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.Customer;
import com.kaishengit.crm.entity.Sale;
import com.kaishengit.exception.ServiceException;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SaleService {


    public List<String> getProgressList();

    void save(Sale sale) throws ServiceException;


    PageInfo<Sale> findMySale(Account account, Map<String,Object> map);
    /**
     * 修改进度
     * @param sale
     */
    void update(Sale sale);

    /**
     * 删除销售机会
     * @param sale
     */
    void del(String id);

    Sale findById(String id);


    List<Sale> findByAccount(Account account);

    List<Sale> findByCustomer(Customer customer);
}
