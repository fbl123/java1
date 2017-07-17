package com.kaishengit.crm.service.impl;

import com.kaishengit.crm.entity.Book;
import com.kaishengit.crm.mapper.BookMapper;
import com.kaishengit.crm.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/7/17.
 */
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookMapper bookMapper;

    @Override
    public List<Book> findBook() {

        return bookMapper.findAll();
    }
}
