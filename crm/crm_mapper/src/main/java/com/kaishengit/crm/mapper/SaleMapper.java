package com.kaishengit.crm.mapper;

import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.Customer;
import com.kaishengit.crm.entity.Sale;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SaleMapper {


    void save(Sale sale);

    List<Sale> findMySale(Account account);


    Sale findByCustId(Customer customer);

    void update(Sale sale);

    void del(Sale sale);
}
