package com.kaishengit.controller;

import com.kaishengit.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/7/14.
 */
@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookService bookService;

    @GetMapping
    public String book(Model model){
        model.addAttribute("bookList",bookService.findAll());
        return "book/list";
    }



}
