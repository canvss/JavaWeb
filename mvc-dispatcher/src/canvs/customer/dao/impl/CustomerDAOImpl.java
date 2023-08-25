package canvs.customer.dao.impl;

import canvs.customer.dao.CustomerDAO;

import canvs.ssm.base.dao.BaseDAO;
import canvs.customer.pojo.Customer;
import canvs.ssm.utils.JDBCUtils;

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
    public int insertCustomer(Customer cust) {
        String sql = "INSERT INTO customer(name,email,birth,salary) VALUES(?,?,?,?)";
        int rows = this.update(conn, sql, cust.getName(), cust.getEmail(), cust.getBirth(), cust.getSalary());
        return rows;
    }

    @Override
    public List<Customer> getCustomerList(int pageNumber, int pageCount) {
        String sql = "SELECT * FROM customer LIMIT ?,?";
        return this.getBeanList(conn, sql, pageNumber * pageCount, pageCount);
    }

    @Override
    public Customer getCustomerById(Integer id) {
        String sql = "SELECT * FROM customer WHERE id = ?";
        return this.getBean(conn, sql, id);
    }

    @Override
    public void updateCustomerById(Customer cust) {
        String sql = "UPDATE customer SET name=?,email=?,birth=?,salary=? WHERE id = ?";
        this.update(conn, sql, cust.getName(), cust.getEmail(), cust.getBirth(), cust.getSalary(), cust.getId());
    }

    @Override
    public void delCustomerById(Integer id) {
        String sql = "DELETE FROM customer WHERE id = ?";
        this.update(conn, sql, id);
    }

    @Override
    public int getCustomerCount() {
        String sql = "SELECT COUNT(*) FROM customer";
        return Integer.parseInt(this.getValue(conn, sql) + "");
    }
}
