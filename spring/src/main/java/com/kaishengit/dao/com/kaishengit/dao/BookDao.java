package com.kaishengit.dao.com.kaishengit.dao;

import com.kaishengit.dao.StuDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Created by Administrator on 2017/7/7.
 */
@Repository("book")
public class BookDao {
    private String bookname;

    public void setBookname(String bookname) {
        this.bookname = bookname;

    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setList(List<StuDao> list) {
        this.list = list;
    }

    public void setMap(Map<String, StuDao> map) {
        this.map = map;
    }

    public void setSet(Set<String> set) {
        this.set = set;
    }

    public void setPro(Properties pro) {
        this.pro = pro;
    }

    private  int number;
    private List<StuDao> list;
    private Map<String,StuDao> map;

    @Override
    public String toString() {
        return "BookDao{" +
                "bookname='" + bookname + '\'' +
                ", number=" + number +
                ", list=" + list +
                ", map=" + map +
                ", set=" + set +
                ", pro=" + pro +
                '}';
    }

    private Set<String> set;
    private Properties pro;






}
