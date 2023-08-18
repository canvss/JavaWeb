package com.canvs.dao;

import com.canvs.pojo.Customer;

import java.sql.Connection;

public interface CustomerDAO {
    void insert(Connection conn, Customer cust);
}
