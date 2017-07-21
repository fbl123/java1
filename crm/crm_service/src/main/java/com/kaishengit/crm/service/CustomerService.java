package com.kaishengit.crm.service;

import com.github.pagehelper.PageInfo;
import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.Customer;
import com.kaishengit.exception.ServiceException;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

public interface CustomerService {
    void save(Customer customer,Account account) throws ServiceException;

    public List<String> findAllSource();
    public List<String> findAllTrade();
    List<Customer> findMyCust(Account account,Map<String,Object> map);

    void update(Customer customer,Account
            oldAccount,Account account,String state);

    Customer findById(String id);

    void del(Customer customer);

    void seas(Customer customer);

    void export(Account account, OutputStream outputStream);

    PageInfo<Customer> findCustomerByNull(Integer pageNo,Integer PageSize);

}
