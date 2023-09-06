package com.canvs.book.dao;

import com.canvs.book.pojo.Order;
import com.canvs.book.pojo.User;

import java.util.List;

public interface OrderDAO {
    void addOrder(Order order);
    List<Order> getOrderList(User user);
    Integer getTotalBookCount(Order order);
}
