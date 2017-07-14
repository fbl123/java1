package com.kaishengit.service.impl;

import com.kaishengit.entity.Book;
import com.kaishengit.mapper.BookMapper;
import com.kaishengit.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
