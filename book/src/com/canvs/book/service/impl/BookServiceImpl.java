package com.canvs.book.service.impl;

import com.canvs.book.dao.BookDAO;
import com.canvs.book.pojo.Book;
import com.canvs.book.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    private BookDAO bookDAO;
    @Override
    public List<Book> getBookList() {
        return bookDAO.getBookList();
    }

    @Override
    public Book getBookById(Integer id) {
        return bookDAO.getBookById(id);
    }
}
