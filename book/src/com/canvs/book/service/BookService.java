package com.canvs.book.service;

import com.canvs.book.pojo.Book;

import java.util.List;

public interface BookService {
    List<Book> getBookList();
    Book getBookById(Integer id);
}
