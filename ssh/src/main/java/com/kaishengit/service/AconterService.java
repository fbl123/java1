package com.kaishengit.service;

import com.kaishengit.dao.AconterDao;
import com.kaishengit.pojo.Aconter;
import com.kaishengit.util.Condition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AconterService {
    @Autowired
    private AconterDao aconterDao;


    public void saveOrUpdate(Aconter aconter){
        aconterDao.save(aconter);
    }
    public Aconter findById(Integer id){
        return aconterDao.findById(id);
    }
    public List<Aconter> findAll(){
        return aconterDao.findAll();
    }
    public void delete(Aconter aconter){
        aconterDao.delete(aconter);
    }
    public List<Aconter> find(Condition... conditions){
        return aconterDao.find(conditions);
    }

}
