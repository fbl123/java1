package com.kaishengit;

import com.kaishengit.pojo.Book;
import com.kaishengit.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.Test;

public class BookTest {


    @Test
    public void test(){
        Session session= HibernateUtil.getSession();
        session.beginTransaction();
        Book book= (Book) session.get(Book.class,3);
        System.out.println(book.getName());
        session.getTransaction().commit();
        Session session1=HibernateUtil.getSession();
        session1.beginTransaction();
        Book book1= (Book) session1.get(Book.class,3);
        System.out.println(book.getName());
        session1.getTransaction().commit();

    }
}
