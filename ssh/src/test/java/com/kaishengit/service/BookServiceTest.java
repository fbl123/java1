package com.kaishengit.service;


import com.kaishengit.dao.BookDao;
import com.kaishengit.pojo.Aconter;
import com.kaishengit.pojo.Book;
import com.kaishengit.util.Condition;
import jdk.nashorn.internal.runtime.FindProperty;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class BookServiceTest {
    @Autowired
    private BookService bookService;
    @Autowired
    private AconterService aconterService;

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
    @Test
    public void saveOrUpdate(){
//        Book book=new Book();
//        book.setName("haha");
//        Aconter aconter=new Aconter();
//        aconter.setName("吴承恩");
//        book.setAconter(aconter);
//        bookService.save(book,aconter);
        Book book=bookService.findId(4);
        System.out.println(book.getName());
        book.setName("西游记");
        bookService.update(book);



    }

    @Test
    public void delete(){
        Aconter aconter=aconterService.findById(4);
        Set<Book> bookSet=aconter.getBookList();
        for(Book book:bookSet){
            System.out.println(book.getName());
        }
    }
    @Test
    public void find1(){
        Condition condition=new Condition("name","西游记","eq");
//        List<Aconter> aconterList=aconterService.find(condition);
//        for(Aconter a:aconterList){
//           for(Book book:a.getBookList()){
//               System.out.println(book.getName()+"----->"+a.getName());
//           }
//        }

        List<Book> books=bookService.find(condition);
        for(Book book:books){
            System.out.println(book.getAconter().getName());
        }

    }

}
