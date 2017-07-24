package com.kaishengit.crm.service;

import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.Customer;
import com.kaishengit.crm.entity.Records;
import org.springframework.stereotype.Service;

import java.util.List;


public interface RecordsService {
    List<Records> findAll(Customer customer, Account account);


}
