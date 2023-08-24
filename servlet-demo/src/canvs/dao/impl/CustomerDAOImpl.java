package canvs.dao.impl;

import canvs.dao.CustomerDAO;

import canvs.dao.base.BaseDAO;
import canvs.pojo.Customer;
import canvs.utils.JDBCUtils;

import java.sql.Connection;
import java.util.List;

public class CustomerDAOImpl extends BaseDAO<Customer> implements CustomerDAO {
    private Connection conn;
    {
        try {
            conn = JDBCUtils.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("数据库连接获取失败...");
        }
    }

    @Override
    public void insertCustomer(Customer cust) {
        String sql = "INSERT INTO customer(name,email,birth,salary) VALUES(?,?,?,?)";
        this.update(conn,sql,cust.getName(),cust.getEmail(),cust.getBirth(),cust.getSalary());
        JDBCUtils.closeResource(conn,null);
    }

    @Override
    public List<Customer> getCustomerList() {
        String sql = "SELECT * FROM customer";
        return this.getForList(conn, sql);
    }

    @Override
    public Customer getCustomerById(Integer id) {
        String sql = "SELECT * FROM customer WHERE id = ?";
        return this.getInstance(conn,sql,id);
    }

    @Override
    public void updateCustomerById(Customer cust) {
        String sql = "UPDATE customer SET name=?,email=?,birth=?,salary=? WHERE id = ?";
        this.update(conn,sql,cust.getName(),cust.getEmail(),cust.getBirth(),cust.getSalary(),cust.getId());
    }

    @Override
    public void delCustomerById(Integer id) {
        String sql = "DELETE FROM customer WHERE id = ?";
        this.update(conn,sql,id);
    }

    @Override
    public int getCustomerCount() {
        String sql = "SELECT COUNT(*) FROM customer";
        return this.getValue(conn,sql);
    }

}
