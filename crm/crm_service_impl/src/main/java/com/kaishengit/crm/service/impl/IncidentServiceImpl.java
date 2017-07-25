package com.kaishengit.crm.service.impl;

import com.kaishengit.crm.entity.Incident;
import com.kaishengit.crm.mapper.IncidentMapper;
import com.kaishengit.crm.service.IncidentService;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class IncidentServiceImpl implements IncidentService {

    @Autowired
    private IncidentMapper incidentMapper;


    @Override
    public void save(Incident incident, String custId, String saleId) {
        if(StringUtils.isNumeric(custId)){
            incident.setCustId(new Integer(custId));
        }
        if(StringUtils.isNumeric(saleId)){
            incident.setSaleId(new Integer(saleId));
        }
        incident.setCreateTime(new Date());
        incident.setState("未完成");
        incidentMapper.save(incident);
    }

    @Override
    public List<Incident> findByAccId(Integer id) {



        return incidentMapper.findByAccId(id);
    }

    @Override
    public void delById(Integer id) {

        incidentMapper.delById(id);

    }

    @Override
    public void update(Incident incident) {
        incidentMapper.update(incident);
    }
}
