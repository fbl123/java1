package com.kaishengit.crm.mapper;

import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.Sale;

import java.util.List;

public interface SaleMapper {


    void save(Sale sale);

    List<Sale> findMySale(Account account);
}
