package com.canvs.customer.service.impl;

import com.canvs.customer.dao.CustomerDAO;
import com.canvs.customer.dao.impl.CustomerDAOImpl;
import com.canvs.customer.pojo.Customer;
import com.canvs.customer.service.CustomerService;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    private CustomerDAO customerDAO = null;

    @Override
    public int addCustomer(Customer customer) {
        return customerDAO.insertCustomer(customer);
    }

    @Override
    public List<Customer> getCustomerList(int pageNumber, int pageCount) {
        return customerDAO.getCustomerList(pageNumber, pageCount);
    }

    @Override
    public void deleteCustomerById(Integer id) {
        customerDAO.delCustomerById(id);
    }

    @Override
    public Customer getCustomerById(Integer id) {
        return customerDAO.getCustomerById(id);
    }

    @Override
    public void updateCustomerById(Customer customer) {
        customerDAO.updateCustomerById(customer);
    }

    @Override
    public int getCustomerCount() {
        return customerDAO.getCustomerCount();
    }
}
