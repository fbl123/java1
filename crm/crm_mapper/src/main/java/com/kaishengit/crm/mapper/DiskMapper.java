package com.kaishengit.crm.mapper;

import com.kaishengit.crm.entity.Disk;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DiskMapper {
    List<Disk> findByPid(@Param("pid") String pid);

    Disk findById(@Param("id") String pid);

    Disk findByPidAndName(@Param("name") String name,@Param("pid") Integer pid);

    void save(Disk disk);

    void update(Disk disk);

    void del(Disk disk);
}
