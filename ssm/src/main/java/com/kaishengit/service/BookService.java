package com.kaishengit.service;

import com.kaishengit.entity.Book;

import java.util.List;

/**
 * Created by Administrator on 2017/7/14.
 */
public interface BookService {

   List<Book> findAll();

   void save(Book book);


    void editBook(Book book);
}
