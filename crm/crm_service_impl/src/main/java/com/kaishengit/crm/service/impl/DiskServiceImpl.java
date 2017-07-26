package com.kaishengit.crm.service.impl;

import com.kaishengit.crm.entity.Disk;
import com.kaishengit.crm.mapper.DiskMapper;
import com.kaishengit.crm.service.DiskService;
import com.kaishengit.exception.ServiceException;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class DiskServiceImpl implements DiskService {

    @Autowired
    private DiskMapper diskMapper;
    @Value( "${upload.file}")
    private String file;
    @Override
    public List<Disk> findByPid(String pid) {
        return diskMapper.findByPid(pid);
    }

    @Override
    public Disk findById(String pid) {
        return diskMapper.findById(pid);
    }

    @Override
    public Disk findByPidAndName(String name, Integer pid) {
        return diskMapper.findByPidAndName(name,pid);
    }

    @Override
    public void save(Disk disk) {

            diskMapper.save(disk);

    }

    @Override
    @Transactional
    public void upload(Disk disk, InputStream input) {
        String saveName= UUID.randomUUID()+disk.getName().substring(disk.getName().lastIndexOf("."));
        disk.setSaveName(saveName);
        disk.setType(Disk.TYPE_FILE);
        disk.setDownLoadCount(0);
        disk.setUploadTime(new Date());
        diskMapper.save(disk);

        try {
            OutputStream out=new FileOutputStream(new File(file,saveName));
            IOUtils.copy(input,out);
            out.flush();
            out.close();
            input.close();

        } catch (IOException e) {
            throw new ServiceException();
        }


    }
}
