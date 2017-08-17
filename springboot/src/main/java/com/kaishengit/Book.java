package com.kaishengit;

public class Book {
    private static Book book=new Book();
   public static int a;
    public static int b=1;

    private Book(){
        a++;
        b++;
    }
    public static Book getBook(){
        return  book;
    }

}
