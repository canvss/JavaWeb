package com.canvs.book.pojo;

import java.util.Date;

public class Order {
    private Integer id;
    private String orderNo;
    private Date date;
    private User orderUser;
    private double orderMoney;
    private Integer orderStatus;

    public Order() {
    }

    public Order(Integer id) {
        this.id = id;
    }
}
