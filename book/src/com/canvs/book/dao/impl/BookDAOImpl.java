package com.canvs.book.dao.impl;

import com.canvs.book.dao.BookDAO;
import com.canvs.book.pojo.Book;
import com.canvs.ssm.base.dao.BaseDAO;

import java.util.List;

public class BookDAOImpl extends BaseDAO<Book> implements BookDAO {
    @Override
    public List<Book> getBookList() {
        return super.getBeanList("SELECT * FROM t_book");
    }

    @Override
    public Book getBookById(Integer id) {
        return super.getBean("SELECT * FROM t_book WHERE id = ?", id);
    }
}
