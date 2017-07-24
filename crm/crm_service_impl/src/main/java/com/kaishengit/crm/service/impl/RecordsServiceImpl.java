package com.kaishengit.crm.service.impl;

import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.Customer;
import com.kaishengit.crm.entity.Records;
import com.kaishengit.crm.entity.Sale;
import com.kaishengit.crm.mapper.RecordsMapper;
import com.kaishengit.crm.mapper.SaleMapper;
import com.kaishengit.crm.service.RecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.Date;
import java.util.List;

@Service
public class RecordsServiceImpl implements RecordsService {

    @Autowired
    private RecordsMapper recordsMapper;
    @Autowired
    private SaleMapper saleMapper;

    @Override
    public List<Records> findAll(Customer customer, Account account) {
        Sale sale= saleMapper.findByCustId(customer);

        return recordsMapper.findBySaleId(sale);
    }




}
