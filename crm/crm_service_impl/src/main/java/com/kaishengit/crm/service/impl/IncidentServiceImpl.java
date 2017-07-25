package com.kaishengit.crm.service.impl;

import com.kaishengit.crm.entity.Incident;
import com.kaishengit.crm.mapper.IncidentMapper;
import com.kaishengit.crm.service.IncidentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class IncidentServiceImpl implements IncidentService {

    @Autowired
    private IncidentMapper incidentMapper;


    @Override
    public void save(Incident incident) {

        incident.setCreateTime(new Date());
        incident.setState(false);
        incidentMapper.save(incident);
    }

    @Override
    public List<Incident> findByAccId(Integer id,String show) {

        return incidentMapper.findByAccId(id,show);
    }

    @Override
    public void delById(Integer id) {

        incidentMapper.delById(id);

    }

    @Override
    public void update(Incident incident) {
        incidentMapper.update(incident);
    }

    @Override
    public Incident findById(String id) {
        return incidentMapper.findById(id);
    }

    @Override
    public List<Incident> findByCustId(Integer id) {
        return incidentMapper.findByCustId(id);
    }
}
