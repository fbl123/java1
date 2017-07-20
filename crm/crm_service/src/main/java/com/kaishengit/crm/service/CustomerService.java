package com.kaishengit.crm.service;

import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.Customer;
import com.kaishengit.exception.ServiceException;

import java.util.List;
import java.util.Map;

public interface CustomerService {
    void save(Customer customer,Account account) throws ServiceException;

    public List<String> findAllSource();
    public List<String> findAllTrade();
    List<Customer> findMyCust(Account account,Map<String,Object> map);

    void update(Customer customer);

}
