package com.kaishengit.crm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaishengit.crm.entity.*;
import com.kaishengit.crm.mapper.*;
import com.kaishengit.crm.service.SaleService;
import com.kaishengit.exception.ServiceException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private IncidentMapper incidentMapper;
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
     * 修改销售进度
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
        Records records=new Records();
        records.setTime(new Date());
        records.setText("将当前进度修改为：["+ sale.getProgress() +"]");
        records.setSaleId(sale.getId());
        recordsMapper.save(records);

    }

    @Override
    @Transactional
    public void del(String id) {
        //删除所有跟进记录
        recordsMapper.delBySaleId(id);
        //删除日程安排
        incidentMapper.delBySaleId(id);
        //todo  删除相关资料
        //删除sale
        saleMapper.delById(id);



    }

    @Override
    public Sale findById(String id) {
        return saleMapper.findById(id);
    }

    @Override
    public List<Sale> findByAccount(Account account) {
        return saleMapper.findMySale(account);
    }

    @Override
    public List<Sale> findByCustomer(Customer customer) {
        return saleMapper.findByCustId(customer);

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
