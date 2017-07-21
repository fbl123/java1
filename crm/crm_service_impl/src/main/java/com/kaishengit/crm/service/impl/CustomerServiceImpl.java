package com.kaishengit.crm.service.impl;

import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.Customer;
import com.kaishengit.crm.mapper.CustomerMapper;
import com.kaishengit.crm.service.CustomerService;
import com.kaishengit.exception.ServiceException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerMapper customerMapper;
    @Value("#{'${customer.trade}'.split(',')}")
    private List<String>  tradeList;
    @Value("#{'${customer.source}'.split(',')}")
    private List<String> sourceList;



    @Override
    public void save(Customer customer, Account account) throws ServiceException {
        //查看客户是否存在
        Customer customer1=customerMapper.findByTell(customer);
        if(customer1!=null){
            throw new ServiceException("已存在");
        }
        //添加客户
        customer.setAccountId(account.getId());
        customer.setCreatTime(new Date());
        customerMapper.save(customer);
    }

    @Override
    public List<String> findAllSource() {
        return sourceList;
    }

    @Override
    public List<String> findAllTrade() {
        return tradeList;
    }

    @Override
    public List<Customer> findMyCust(Account account,Map<String,Object> map) {
        Customer customer=new Customer();
        customer.setAccountId(account.getId());
        if(StringUtils.isNotBlank((String) map.get("keyword"))){
            customer.setCustName("%"+(String) map.get("keyword")+"%");

        }

        return customerMapper.findByAccId(customer);
    }

    @Override
    public void update(Customer customer,Account oldAccount,Account account,String state) {
        customer.setAccountId(account.getId());
        customer.setUpdateTime(new Date());
        customerMapper.update(customer);
    }

    @Override
    public Customer findById(String id) {
        return customerMapper.findById(id);
    }

    @Override
    public void del(Customer customer) {
        //TODO


        customerMapper.del(customer);
    }
//将客户放入到公海中
    @Override
    public void seas(Customer customer) {

        customer.setAccountId(0);
        customerMapper.update(customer);
    }

    //将我的客户数据到处Excel
    @Override
    public void export(Account account, OutputStream outputStream) {

    }
}
