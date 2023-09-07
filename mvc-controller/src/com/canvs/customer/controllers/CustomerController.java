package com.canvs.customer.controllers;

import com.canvs.customer.dao.impl.CustomerDAOImpl;
import com.canvs.customer.exception.DateException;
import com.canvs.customer.pojo.Customer;
import com.canvs.ssm.mvc.ViewBaseServlet;
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

    protected String customerList(Integer pageNo, HttpServletRequest request) throws IOException {
        if (pageNo == null) {
            pageNo = 0;
        }
        int pageCount = 5;
        int tailPageNo = 0;
        List<Customer> customerList = dao.getCustomerList(pageNo, pageCount);
        int count = dao.getCustomerCount();
        if (count > 0) {
            tailPageNo = (count - 1) / 5 + 1;
        }
        request.getSession().setAttribute("tailPageNo", tailPageNo);
        request.getSession().setAttribute("pageNo", pageNo);
        request.getSession().setAttribute("pageCount", pageCount);
        request.getSession().setAttribute("customerList", customerList);
        return "index";
    }

    protected String addCustomer(String name, String email, String birth, String salary) throws DateException {
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
                return "redirect:customer.do";
            } catch (ParseException e) {
                throw new DateException("时间格式错误");
            }
        } else {
            return "add";
        }
    }

    protected String deleteCustomerById(Integer id) throws IOException {
        dao.delCustomerById(id);
        return "redirect:customer.do";
    }

    protected String editCustomerById(Integer id, String name, String email, String birth, String salary, HttpServletRequest request) throws IOException {
        if (id != null && Utils.isNotEmpty(name) && Utils.isNotEmpty(email) && Utils.isNotEmpty(birth) && Utils.isNotEmpty(salary)) {
            try {
                java.sql.Date date = Utils.dateFormat(birth);
                Customer cust = new Customer(id, name, email, date, Double.parseDouble(salary));
                dao.updateCustomerById(cust);
                return "redirect:customer.do";
            } catch (ParseException e) {
                throw new RuntimeException("时间格式有误....");
            }
        } else {
            Customer cust = dao.getCustomerById(id);
            request.setAttribute("cust", cust);
            return "edit";
        }
    }
}
