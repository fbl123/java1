package com.kaishengit.service.impl;

import com.kaishengit.entity.Book;
import com.kaishengit.mapper.BookMapper;
import com.kaishengit.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/14.
 */
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookMapper bookMapper;

    @Override
    public List<Book> findAll() {
        return bookMapper.findAll();
    }

    @Override
    public void save(Book book) {

            bookMapper.save(book);


    }

    @Override
    public void editBook(Book book) {
        bookMapper.upda(book);
    }

    @Override
    public void delById(String id) {

        bookMapper.delById(id);
    }

    @Override
    public List<Book> findByParam(String key, String value) {

       Map<String,Object> map=new HashMap<>();
        System.out.println(key);
       map.put("key",key);
       map.put("value",value);
       return bookMapper.findByParam(map);

    }

    @Override
    public Book findByid(String id) {
        return bookMapper.findById(id);
    }


}
