package com.kaishengit;

public class Book {

    public static int a;
    public static int b=1;
    private static Book book=new Book();

    private Book(){
        a++;
        b++;
    }
    public static Book getBook(){
        return  book;
    }

}
