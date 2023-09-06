package com.canvs.book.controller;

import com.canvs.book.pojo.Order;
import com.canvs.book.pojo.User;
import com.canvs.book.service.OrderService;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class OrderController {
    private OrderService orderService;
    public String checkout(HttpSession session){
        Order order = new Order();
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        User user = (User) session.getAttribute("currUser");
        order.setOrderNo(UUID.randomUUID().toString().replaceAll("-", "")+"_"+sdf.format(now));
        order.setOrderDate(now);
        order.setOrderUser(user);
        order.setOrderMoney(user.getCart().getTotalMoney());
        order.setOrderStatus(0);
        orderService.addOrder(order);
        return "cart/checkout";
    }
    public String orderList(HttpSession session){
        User user = (User) session.getAttribute("currUser");
        List<Order> orderList = orderService.orderList(user);
        orderList.forEach(System.out::println);
        user.setOrderList(orderList);
        session.setAttribute("currUser",user);
        return "order/order";
    }
}
