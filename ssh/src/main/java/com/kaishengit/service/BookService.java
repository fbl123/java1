package com.kaishengit.service;

import com.kaishengit.dao.BookDao;
import com.kaishengit.pojo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class BookService {

    @Autowired
    private BookDao bookDao;





    public List<Book> findAll(){


        return bookDao.findAll();
    }

    public List<Book> findByParames(Map<String,Object> maps){
        return bookDao.findByProperty(maps);
    }

}
