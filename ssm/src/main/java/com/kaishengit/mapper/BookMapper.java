package com.kaishengit.mapper;

import com.kaishengit.entity.Book;

import java.util.List;

/**
 * Created by Administrator on 2017/7/14.
 */
public interface BookMapper {

    List<Book> findAll();

    void save(Book book);

    void upda(Book book);
}
