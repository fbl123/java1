package com.kaishengit.service.impl;

import com.kaishengit.entity.Book;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.mapper.BookMapper;
import com.kaishengit.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
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

    @Override
    public void save(Book book) {

            bookMapper.save(book);


    }

    @Override
    public void editBook(Book book) {
        bookMapper.upda(book);
    }


}
