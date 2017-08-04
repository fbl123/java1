package com.kaishengit.service;

import com.kaishengit.dao.AconterDao;
import com.kaishengit.dao.BookDao;
import com.kaishengit.pojo.Aconter;
import com.kaishengit.pojo.Book;
import com.kaishengit.util.Condition;
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
    @Autowired
    private AconterDao aconterDao;




    public List<Book> findAll(){


        return bookDao.findAll();
    }

    public List<Book> findByParames(Map<String,Object> maps){
        return bookDao.findByProperty(maps);
    }

    public void save(Book book, Aconter aconter){
        bookDao.save(book);
        aconterDao.save(aconter);
    }

    public void update(Book book){
        bookDao.save(book);
    }

    public Book findId(Integer id) {
        return bookDao.findById(id);
    }

    public List<Book> find(Condition... conditions) {
        return bookDao.find(conditions);
    }
}
