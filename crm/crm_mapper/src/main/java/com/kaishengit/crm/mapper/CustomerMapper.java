package com.kaishengit.crm.mapper;

import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.Customer;

import java.util.List;

public interface CustomerMapper {


     void save(Customer customer);

    Customer findByTell(Customer customer);

    List<Customer> findByAccId(Customer customer);

    void update(Customer customer);

    Customer findById(String id);

    void del(Customer customer);
}
