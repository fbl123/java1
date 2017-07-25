package com.kaishengit.crm.service;

import com.kaishengit.crm.entity.Incident;

import java.util.List;

public interface IncidentService {
    void save(Incident incident);

    List<Incident> findByAccId(Integer id,String show);

    void delById(Integer id);

    void update(Incident incident);

    Incident findById(String id);
}
