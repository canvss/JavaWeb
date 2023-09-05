package com.canvs.book.controller;

import com.canvs.book.pojo.Book;
import com.canvs.book.service.BookService;

import javax.servlet.http.HttpSession;
import java.util.List;

public class BookController {
    private BookService bookService;
    public String index(HttpSession session){
        List<Book> bookList = bookService.getBookList();
        bookList.forEach(System.out :: println);
        session.setAttribute("bookList",bookList);
        return "index";
    }
}
