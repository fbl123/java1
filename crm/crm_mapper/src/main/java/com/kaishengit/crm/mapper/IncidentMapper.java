package com.kaishengit.crm.mapper;

import com.kaishengit.crm.entity.Customer;
import com.kaishengit.crm.entity.Incident;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IncidentMapper {
    void save(Incident incident);

    List<Incident> findByAccId(@Param("accid") Integer id,@Param("state") String show);

    void delById(@Param("id") Integer id);

    void delByCustId(Customer customer);

    void delBySaleId(@Param("id") String id);

    void update(Incident incident);

    void updateByCust(Customer customer);

    Incident findById(@Param("id") String id);

    List<Incident> findByCustId(@Param("custid") Integer id);

}
