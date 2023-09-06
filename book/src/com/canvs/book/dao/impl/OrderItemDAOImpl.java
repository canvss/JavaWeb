package com.canvs.book.dao.impl;


import com.canvs.book.dao.OrderItemDAO;
import com.canvs.book.pojo.OrderItem;
import com.canvs.ssm.base.dao.BaseDAO;

public class OrderItemDAOImpl extends BaseDAO<OrderItem> implements OrderItemDAO {
    @Override
    public void addOrderItem(OrderItem orderItem) {
        String sql = "INSERT INTO t_order_item VALUES(0,?,?,?);";
        super.executeUpdate(sql,orderItem.getBook().getId(),orderItem.getBuyCount(),orderItem.getOrderBean().getId());
    }
}
