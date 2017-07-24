package com.kaishengit.crm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.Customer;
import com.kaishengit.crm.entity.Records;
import com.kaishengit.crm.entity.Sale;
import com.kaishengit.crm.mapper.AccountMapper;
import com.kaishengit.crm.mapper.CustomerMapper;
import com.kaishengit.crm.mapper.RecordsMapper;
import com.kaishengit.crm.mapper.SaleMapper;
import com.kaishengit.crm.service.SaleService;
import com.kaishengit.exception.ServiceException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SaleServiceImpl implements SaleService {
    @Autowired
    private SaleMapper saleMapper;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private RecordsMapper recordsMapper;
    @Value("#{'${sale.progress}'.split(',')}")
    private List<String> progressList;

    @Override
    public List<String> getProgressList(){
        return progressList;
    }

    @Override
    @Transactional
    public void save(Sale sale) throws ServiceException {
        //添加销售机会
        sale.setCreateTime(new Date());
        sale.setLastTime(new Date());
        saleMapper.save(sale);
        //更改客户最后跟进记录
       Customer customer= customerMapper.findById(sale.getCustomerId().toString());
       customer.setFollowTime(new Date());
       customerMapper.update(customer);
       //添加跟进记录
        Records records=saveRecord(sale);
        if(records!=null){
            recordsMapper.save(records);
        }


    }

    @Override
    public PageInfo<Sale> findMySale(Account account,Map<String,Object> map) {
        PageHelper.startPage((int)map.get("page"),(int)map.get("size"));
        List<Sale> list=saleMapper.findMySale(account);

        return new PageInfo<>(list);
    }


    @Override
    @Transactional
    /**
     * 修改销售机会
     * @param sale
     */
    public void update(Sale sale) {
        //修改销售机会
        sale.setLastTime(new Date());
        saleMapper.update(sale);
        //更改客户最后跟进记录
        Customer customer= customerMapper.findById(sale.getCustomerId().toString());
        customer.setFollowTime(new Date());
        customerMapper.update(customer);
        //添加跟进记录
        Records records=saveRecord(sale);
        if(records!=null){
            recordsMapper.save(records);
        }

    }

    @Override
    @Transactional
    public void del(Sale sale) {
        //删除所有跟进记录
        recordsMapper.delBySale(sale);
        //todo  删除日程安排
        //todo  删除相关资料
        //删除sale
        saleMapper.del(sale);



    }

    public static Records saveRecord(Sale sale){
        if(StringUtils.isNotBlank(sale.getDescription())){
            Records records=new Records();
            records.setSaleId(sale.getId());
            records.setText(sale.getDescription());
            records.setTime(new Date());
            return records;
        }
        return null;
    }
}
