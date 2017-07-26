package com.kaishengit.crm.service;

import com.kaishengit.crm.entity.Disk;

import java.io.InputStream;
import java.util.List;

public interface DiskService {

    List<Disk> findByPid(String pid);

    Disk findById(String pid);

    Disk findByPidAndName(String name, Integer pid);

    void save(Disk disk);

    void upload(Disk disk, InputStream input);
}
