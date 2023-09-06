package com.canvs.book.pojo;

import java.util.Date;

public class Order {
    private Integer id;
    private String orderNo;
    private Date orderDate;
    private User orderUser;
    private double orderMoney;
    private Integer orderStatus;

    private Integer totalBookCount;

    public Integer getTotalBookCount() {
        return totalBookCount;
    }

    public void setTotalBookCount(Integer totalBookCount) {
        this.totalBookCount = totalBookCount;
    }

    public Order(String orderNo, Date date, User orderUser, double orderMoney, Integer orderStatus) {
        this.orderNo = orderNo;
        this.orderDate = date;
        this.orderUser = orderUser;
        this.orderMoney = orderMoney;
        this.orderStatus = orderStatus;
    }

    public Order() {
    }

    public Order(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public User getOrderUser() {
        return orderUser;
    }

    public void setOrderUser(User orderUser) {
        this.orderUser = orderUser;
    }

    public double getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(double orderMoney) {
        this.orderMoney = orderMoney;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderNo='" + orderNo + '\'' +
                ", date=" + orderDate +
                ", orderUser=" + orderUser +
                ", orderMoney=" + orderMoney +
                ", orderStatus=" + orderStatus +
                '}';
    }
}
