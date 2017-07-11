package com.kaishengit.Service;

import com.kaishengit.dao.StuDao;
import com.kaishengit.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2017/7/11.
 * @Transactional 属性 isolation:设置隔离级别 propagation:设置传播属性 rollbackFor:回滚类型
 */
@Service
public class DaoService {
    @Autowired
    StuDao studao;
    @Transactional
    public void save(){
        Student stu=new Student();
        stu.setName("313213");
        stu.setPassword("21");
        stu.setClass_id(1);

        studao.inster(stu);
//        if(true){
//            throw new RuntimeException();
//        }
        studao.inster(stu);


    }
    public List<Student> findAll(){
        return studao.findAll();
    }

}
