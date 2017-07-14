package com.kaishengit.controller;

import com.kaishengit.entity.Book;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.BookService;
import com.kaishengit.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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


    @GetMapping("/add")
    public String save(){
        return "book/add";
    }
    @RequestMapping(value = "/add",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public Result save(Book book){
        Result res;
        try{
            bookService.save(book);
            res = new Result("success");
        }catch(ServiceException e){
            res = new Result("error",e.getMessage());
        }

        return res;
    }



   @GetMapping(value = "/josn",produces = "application/json;charset=utf-8")
   @ResponseBody
    public Result haha(){
        Result res=new Result("success");
        res.setData("Ok");
        return res;
    }


}
