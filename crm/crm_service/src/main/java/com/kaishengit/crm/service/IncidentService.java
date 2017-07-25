package com.kaishengit.crm.service;

import com.kaishengit.crm.entity.Incident;

import java.util.List;

public interface IncidentService {
    void save(Incident incident, String custId, String saleId);

    List<Incident> findByAccId(Integer id);

    void delById(Integer id);

    void update(Incident incident);

}
