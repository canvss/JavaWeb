package com.canvs.book.pojo;

import java.util.List;
import java.util.concurrent.PriorityBlockingQueue;

public class OrderItem {
    private Integer id;
    private Book book;
    private Integer buyCount;
    private Order orderBean;
    private List<OrderItem> orderItemList;

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public OrderItem() {
    }

    public OrderItem(Integer id, Book book, Integer buyCount, Order orderBean) {
        this.id = id;
        this.book = book;
        this.buyCount = buyCount;
        this.orderBean = orderBean;
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
