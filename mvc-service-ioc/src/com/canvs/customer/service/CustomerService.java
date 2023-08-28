package com.canvs.customer.service;

import com.canvs.customer.pojo.Customer;

import java.util.List;

public interface CustomerService {
    int addCustomer(Customer customer);
    List<Customer> getCustomerList(int pageNumber, int pageCount);
    void deleteCustomerById(Integer id);
    Customer getCustomerById(Integer id);
    void updateCustomerById(Customer customer);
    int getCustomerCount();
}
