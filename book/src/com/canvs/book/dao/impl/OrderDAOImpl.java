package com.canvs.book.dao.impl;

import com.canvs.book.dao.OrderDAO;
import com.canvs.book.pojo.Order;
import com.canvs.book.pojo.User;
import com.canvs.ssm.base.dao.BaseDAO;

import java.math.BigDecimal;
import java.util.List;

public class OrderDAOImpl extends BaseDAO<Order> implements OrderDAO {
    @Override
    public void addOrder(Order order) {
        String sql = "INSERT INTO t_order VALUES(0,?,?,?,?,?)";
        int orderId = super.executeUpdate(sql, order.getOrderNo(), order.getOrderDate(), order.getOrderUser().getId(), order.getOrderMoney(), order.getOrderStatus());
        order.setId(orderId);
    }

    @Override
    public List<Order> getOrderList(User user) {
        String sql = "SELECT * FROM t_order WHERE orderUser = ?";
        return super.getBeanList(sql, user.getId());
    }

    @Override
    public Integer getTotalBookCount(Order order) {
        String sql = "SELECT SUM(t3.buyCount) AS totalBookCount,t3.orderBean FROM( SELECT t1.id,t2.buyCount,t2.orderBean FROM t_order AS t1 INNER JOIN t_order_item AS t2 ON t1.id = t2.orderBean WHERE t1.orderUser=?) t3 GROUP BY t3.orderBean HAVING t3.orderBean = ?";
        BigDecimal bigDecimal = super.getValue(sql, order.getOrderUser().getId(), order.getId());
        return bigDecimal.intValue();
    }
}
