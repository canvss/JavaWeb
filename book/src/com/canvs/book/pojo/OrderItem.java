package com.canvs.book.pojo;

import java.util.concurrent.PriorityBlockingQueue;

public class OrderItem {
    private Integer id;
    private Book book;
    private Integer buyCount;
    private Order orderBean;

    public OrderItem() {
    }

    public OrderItem(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }

    public Order getOrderBean() {
        return orderBean;
    }

    public void setOrderBean(Order orderBean) {
        this.orderBean = orderBean;
    }
}
