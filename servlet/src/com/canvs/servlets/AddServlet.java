package com.canvs.servlets;


import com.canvs.dao.impl.CustomerDAOImpl;
import com.canvs.pojo.Customer;
import com.canvs.utils.JDBCUtils;
import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.BitSet;

public class AddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码格式
        req.setCharacterEncoding("utf-8");
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        Customer cust = new Customer(Integer.parseInt(id), name, email, new Date(16737635569L));
        CustomerDAOImpl dao = new CustomerDAOImpl();
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            dao.insert(conn,cust);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeResource(conn,null);
        }
    }
}
