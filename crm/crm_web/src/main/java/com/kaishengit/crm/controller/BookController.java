package com.kaishengit.crm.controller;

import com.kaishengit.crm.entity.Book;
import com.kaishengit.crm.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2017/7/17.
 */
@Controller
public class BookController {
    @Autowired
   private BookService bookService;

    @RequestMapping(value="/book",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<Book> book(){
        List<Book> book=bookService.findBook();
        return book;
    }

}
