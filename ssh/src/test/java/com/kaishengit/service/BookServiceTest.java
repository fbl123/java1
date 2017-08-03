package com.kaishengit.service;


import com.kaishengit.pojo.Book;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class BookServiceTest {
    @Autowired
    private BookService bookService;


    @org.junit.Test
    public void findAll() throws Exception {

        List<Book> books=bookService.findAll();
        for(Book book:books){
            System.out.println(book.getAconter().getName());
        }
    }
    @org.junit.Test
    public void find(){
        Map<String,Object> maps=new HashMap<String, Object>();
        maps.put("name","缥缈录1");
        List<Book> books=bookService.findByParames(maps);
        for(Book book:books){
            System.out.println(book.getAconter().getName());
        }


    }


}
