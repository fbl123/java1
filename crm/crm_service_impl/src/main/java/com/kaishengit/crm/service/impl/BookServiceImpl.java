package com.kaishengit.crm.service.impl;

import com.kaishengit.crm.mapper.BookMapper;
import com.kaishengit.crm.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/7/17.
 */
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookMapper bookMapper;

    @Override
    public void findBook() {

    }
}
