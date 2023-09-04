package com.canvs.customer.controllers;

import com.canvs.customer.dao.impl.CustomerDAOImpl;
import com.canvs.customer.exception.DateException;
import com.canvs.customer.pojo.Customer;
import com.canvs.ssm.base.mvc.ViewBaseServlet;
import com.canvs.ssm.utils.Utils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class CustomerServlet extends ViewBaseServlet {
    private CustomerDAOImpl dao = new CustomerDAOImpl();
    private ServletContext servletContext;
    public void setServletContext(ServletContext servletContext) throws ServletException {
        this.servletContext = servletContext;
        super.init(this.servletContext);
    }
    protected void customerList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int pageNo = 0;
        int pageCount = 5;
        int tailPageNo = 0;
        String number = request.getParameter("pageNo");
        if (Utils.isNotEmpty(number)) {
            pageNo = Integer.parseInt(number);
        }
        List<Customer> customerList = dao.getCustomerList(pageNo, pageCount);
        int count = dao.getCustomerCount();
        if (count > 0) {
            tailPageNo = count / 5;
        }
        request.getSession().setAttribute("tailPageNo", tailPageNo);
        request.getSession().setAttribute("pageNo", pageNo);
        request.getSession().setAttribute("pageCount", pageCount);
        request.getSession().setAttribute("customerList", customerList);
        this.processTemplate("index", request, response);
    }

    protected void addCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException, DateException, InterruptedException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String birth = request.getParameter("birth");
        String salary = request.getParameter("salary");
        if (Utils.isNotEmpty(name) && Utils.isNotEmpty(email) && Utils.isNotEmpty(birth) && Utils.isNotEmpty(salary)) {
            try {
                java.sql.Date date = Utils.dateFormat(birth);
                Customer cust = new Customer(name, email, date, Double.parseDouble(salary));
                int rows = dao.insertCustomer(cust);
                if (rows > 0) {
                    System.out.println(cust + "  --> 添加成功");
                } else {
                    System.out.println(cust + "  --> 添加失败");
                }
                response.sendRedirect(request.getServletPath() + "?method=customerList");
            } catch (ParseException e) {
                throw new DateException("时间格式错误");
            }
        } else {
            this.processTemplate("add", request, response);
        }
    }

    protected void deleteCustomerById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        dao.delCustomerById(id);
        response.sendRedirect(request.getServletPath()+"?method=customerList");
    }

    protected void editCustomerById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String birth = request.getParameter("birth");
        String salary = request.getParameter("salary");
        if (Utils.isNotEmpty(id) && Utils.isNotEmpty(name) && Utils.isNotEmpty(email) && Utils.isNotEmpty(birth) && Utils.isNotEmpty(salary)) {
            try {
                java.sql.Date date = Utils.dateFormat(birth);
                Customer cust = new Customer(Integer.parseInt(id), name, email, date, Double.parseDouble(salary));
                dao.updateCustomerById(cust);
                response.sendRedirect(request.getServletPath() + "?method=customerList");
            } catch (ParseException e) {
                throw new RuntimeException("时间格式有误....");
            }
        } else {
            Customer cust = dao.getCustomerById(Integer.parseInt(id));
            request.setAttribute("cust", cust);
            this.processTemplate("edit", request, response);
        }
    }
}
