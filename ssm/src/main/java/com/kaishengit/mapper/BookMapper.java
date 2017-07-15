package com.kaishengit.mapper;

import com.kaishengit.entity.Book;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/14.
 */
public interface BookMapper {

    List<Book> findAll();

    void save(Book book);

    void upda(Book book);

    void delById(String id);

    List<Book> findByParam(Map<String, Object> map);
}
