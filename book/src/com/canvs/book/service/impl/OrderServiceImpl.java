package com.canvs.book.service.impl;

import com.canvs.book.dao.CartItemDAO;
import com.canvs.book.dao.OrderDAO;
import com.canvs.book.dao.OrderItemDAO;
import com.canvs.book.pojo.CartItem;
import com.canvs.book.pojo.Order;
import com.canvs.book.pojo.OrderItem;
import com.canvs.book.pojo.User;
import com.canvs.book.service.OrderService;

import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    private OrderDAO orderDAO;
    private OrderItemDAO orderItemDAO;
    private CartItemDAO cartItemDAO;
    @Override
    public void addOrder(Order order) {
        orderDAO.addOrder(order);
        User orderUser = order.getOrderUser();
        Map<Integer, CartItem> cartItemMap = orderUser.getCart().getCartItemMap();
        for (CartItem cartItem : cartItemMap.values()){
            OrderItem orderItem = new OrderItem();
            orderItem.setBook(cartItem.getBook());
            orderItem.setBuyCount(cartItem.getBuyCount());
            orderItem.setOrderBean(order);
            orderItemDAO.addOrderItem(orderItem);
        }
        for (CartItem cartItem : cartItemMap.values()){
            cartItemDAO.deleteCartItem(cartItem);
        }
    }

    @Override
    public List<Order> orderList(User user) {

        List<Order> orderList = orderDAO.getOrderList(user);
        for (Order order:orderList){
            order.setOrderUser(user);
            Integer totalBookCount = orderDAO.getTotalBookCount(order);
            order.setTotalBookCount(totalBookCount);
        }
        return orderList;
    }
}
