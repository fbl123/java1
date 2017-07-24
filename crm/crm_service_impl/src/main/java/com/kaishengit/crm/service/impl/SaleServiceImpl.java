package com.kaishengit.crm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.Sale;
import com.kaishengit.crm.mapper.SaleMapper;
import com.kaishengit.crm.service.SaleService;
import com.kaishengit.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SaleServiceImpl implements SaleService {
    @Autowired
    private SaleMapper saleMapper;
    @Value("#{'${sale.progress}'.split(',')}")
    private List<String> progressList;

    @Override
    public List<String> getProgressList(){
        return progressList;
    }

    @Override
    public void save(Sale sale) throws ServiceException {
        saleMapper.save(sale);
    }

    @Override
    public PageInfo<Sale> findMySale(Account account,Map<String,Object> map) {
        PageHelper.startPage((int)map.get("page"),(int)map.get("size"));
        List<Sale> list=saleMapper.findMySale(account);

        return new PageInfo<>(list);
    }


}
