package com.canvs.dao.impl;

import com.canvs.dao.CustomerDAO;
import com.canvs.dao.base.BaseDAO;
import com.canvs.pojo.Customer;

import java.sql.Connection;

public class CustomerDAOImpl extends BaseDAO<Customer> implements CustomerDAO {
    @Override
    public void insert(Connection conn, Customer cust) {
        String sql = "INSERT INTO customer VALUES(?,?,?,?)";
        update(conn,sql,cust.getId(),cust.getName(),cust.getEmail(),cust.getBirth());
    }
}
