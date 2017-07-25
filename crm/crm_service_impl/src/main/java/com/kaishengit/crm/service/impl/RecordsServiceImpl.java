package com.kaishengit.crm.service.impl;

import com.kaishengit.crm.entity.Customer;
import com.kaishengit.crm.entity.Records;
import com.kaishengit.crm.entity.Sale;
import com.kaishengit.crm.mapper.CustomerMapper;
import com.kaishengit.crm.mapper.RecordsMapper;
import com.kaishengit.crm.mapper.SaleMapper;
import com.kaishengit.crm.service.RecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class RecordsServiceImpl implements RecordsService {

    @Autowired
    private RecordsMapper recordsMapper;
    @Autowired
    private SaleMapper saleMapper;
    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public List<Records> findAll(Sale sale) {
        return recordsMapper.findBySaleId(sale);
    }

    @Override
    @Transactional
    public void save(Records records) {
        //添加跟进记录
        recordsMapper.save(records);
        //修改sale跟进时间
        Sale sale=saleMapper.findById(records.getSaleId().toString());
        sale.setLastTime(new Date());
        //修改客户跟进时间
        Customer customer=customerMapper.findById(sale.getCustomerId().toString());
        customer.setFollowTime(new Date());
    }


}
