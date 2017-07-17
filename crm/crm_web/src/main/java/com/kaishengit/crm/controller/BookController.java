package com.kaishengit.crm.controller;

import com.kaishengit.crm.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by Administrator on 2017/7/17.
 */
@Controller
public class BookController {
    @Autowired
   private BookService bookService;

}
