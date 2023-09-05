package com.canvs.book.dao;

import com.canvs.book.pojo.Book;

import java.util.List;

public interface BookDAO {
    List<Book> getBookList();
    Book getBookById(Integer id);
}
