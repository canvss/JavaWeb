package com.canvs.customer.controllers;

import com.canvs.customer.exception.CustomerServletException;
import com.canvs.customer.exception.DateException;
import com.canvs.customer.pojo.Customer;
import com.canvs.customer.service.CustomerService;
import com.canvs.ssm.base.mvc.ViewBaseServlet;
import com.canvs.ssm.utils.Utils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CustomerController extends ViewBaseServlet {
    private CustomerService customerService = null;

    protected String customerList(Integer pageNo, HttpServletRequest request) {
        if (pageNo == null) {
            pageNo = 0;
        }
        int pageCount = 5;
        int tailPageNo = 0;
        List<Customer> customerList = customerService.getCustomerList(pageNo, pageCount);
        int count = customerService.getCustomerCount();
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
                int rows = customerService.addCustomer(cust);
                if (rows > 0) {
                    System.out.println(cust + "  --> 添加成功");
                } else {
                    System.out.println(cust + "  --> 添加失败");
                }
                return "redirect:customer.do";
            } catch (Exception e) {
                throw new CustomerServletException("时间格式错误");
            }
        } else {
            return "add";
        }
    }

    protected String deleteCustomerById(Integer id) {
        customerService.deleteCustomerById(id);
        return "redirect:customer.do";
    }

    protected String editCustomerById(Integer id, String name, String email, String birth, String salary, HttpServletRequest request)  {
        if (id != null && Utils.isNotEmpty(name) && Utils.isNotEmpty(email) && Utils.isNotEmpty(birth) && Utils.isNotEmpty(salary)) {
            try {
                java.sql.Date date = Utils.dateFormat(birth);
                Customer cust = new Customer(id, name, email, date, Double.parseDouble(salary));
                customerService.updateCustomerById(cust);
                return "redirect:customer.do";
            } catch (Exception e) {
                throw new CustomerServletException("时间格式有误....");
            }
        } else {
            Customer cust = customerService.getCustomerById(id);
            request.setAttribute("cust", cust);
            return "edit";
        }
    }
}
