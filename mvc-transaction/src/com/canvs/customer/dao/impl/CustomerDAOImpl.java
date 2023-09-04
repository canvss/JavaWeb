package com.canvs.customer.dao.impl;

import com.canvs.customer.dao.CustomerDAO;
import com.canvs.customer.pojo.Customer;
import com.canvs.ssm.base.dao.BaseDAO;
import com.canvs.ssm.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

public class CustomerDAOImpl extends BaseDAO<Customer> implements CustomerDAO {
    @Override
    public int insertCustomer(Customer cust) {
        String sql = "INSERT INTO customer(name,email,birth,salary) VALUES(?,?,?,?)";
        int rows = this.update(sql, cust.getName(), cust.getEmail(), cust.getBirth(), cust.getSalary());
        return rows;
    }

    @Override
    public List<Customer> getCustomerList(int pageNumber, int pageCount) {
        String sql = "SELECT * FROM customer LIMIT ?,?";
        return this.getBeanList(sql, pageNumber * pageCount, pageCount);
    }

    @Override
    public Customer getCustomerById(Integer id) {
        String sql = "SELECT * FROM customer WHERE id = ?";
        return this.getBean(sql, id);
    }

    @Override
    public void updateCustomerById(Customer cust) {
        String sql = "UPDATE customer SET name=?,email=?,birth=?,salary=? WHERE id = ?";
        this.update(sql, cust.getName(), cust.getEmail(), cust.getBirth(), cust.getSalary(), cust.getId());
    }

    @Override
    public void delCustomerById(Integer id) {
        String sql = "DELETE FROM customer WHERE id = ?";
        this.update(sql, id);
    }

    @Override
    public int getCustomerCount() {
        String sql = "SELECT COUNT(*) FROM customer";
        return Integer.parseInt(this.getValue(sql) + "");
    }
}
