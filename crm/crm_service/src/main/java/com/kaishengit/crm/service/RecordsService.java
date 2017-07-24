package com.kaishengit.crm.service;

import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.Customer;
import com.kaishengit.crm.entity.Records;
import com.kaishengit.crm.entity.Sale;
import org.springframework.stereotype.Service;

import java.util.List;


public interface RecordsService {
    List<Records> findAll(Sale sale);


}
