package com.kaishengit.crm.mapper;

import com.kaishengit.crm.entity.Records;
import com.kaishengit.crm.entity.Sale;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RecordsMapper {

    void save(Records records);

    List<Records> findBySaleId(Sale sale);

    void delBySale(Sale sale);

    void delBySaleId(@Param("id") String id);
}
