package com.kaishengit.controller;

import com.kaishengit.entity.Book;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.BookService;
import com.kaishengit.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2017/7/14.
 */
@Controller
@RequestMapping("/book")
public class BookController {
    Logger logger= LoggerFactory.getLogger(BookController.class);
    @Autowired
    BookService bookService;

    @GetMapping
    public String book(Model model,String key,String value){
       key= com.kaishengit.utils.StringUtils.newString(key);
        value=com.kaishengit.utils.StringUtils.newString(value);
        logger.debug("key:{}",key);
        logger.debug("value:{}",value);

        model.addAttribute("bookList",bookService.findByParam(key,value));
        System.out.println("------------------------------>"+bookService.findByParam(key,value));
        return "book/list";
    }

    /**
     * 添加书籍
     * @return
     */
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

    /**
     * 修改书籍
     * @return
     */
    @GetMapping(value="/edit",produces = "application/json;charset=utf-8")
    @ResponseBody
    public Result edit(Model model,String id){
        Result res;
        if(org.apache.commons.lang3.StringUtils.isNumeric(id)){
            try{
                Book book=bookService.findByid(id);
                res=new Result("success");
                res.setData(book);
            }  catch (ServiceException e){
                res=new Result("error",e.getMessage());
            }
        }else{
            res=new Result("error","参数异常");
        }

        return res;
    }
    @RequestMapping(value="/edit",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public Result edit(Book book){
        Result res;
        try{
            bookService.editBook(book);
            res=new Result("success");
        }catch (ServiceException e){
            res = new Result("error",e.getMessage());
        }

        return res;
    }

    /**
     * 删除
     * @return
     */
    @RequestMapping(value="/del",produces = "applocation/josn;charset=utf-8")
    @ResponseBody
    public Result delBook(String  id){
        Result res;
        if(org.apache.commons.lang3.StringUtils.isNumeric(id)){
               bookService.delById(id);
               res = new Result("success");
        }else{
            res = new Result("error","参数错误");
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
