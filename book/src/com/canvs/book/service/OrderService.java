package com.canvs.book.service;

import com.canvs.book.pojo.Order;
import com.canvs.book.pojo.User;

import java.util.List;

public interface OrderService {
    void addOrder(Order order);
    List<Order> orderList(User user);
}
