package com.canvs.dao;


import com.canvs.pojo.Customer;

import java.util.List;

public interface CustomerDAO {
    void insertCustomer(Customer cust);
    List<Customer> getCustomerList(int pageNumber,int pageCount);
    Customer getCustomerById(Integer id);
    void updateCustomerById(Customer cust);
    void delCustomerById(Integer id);
    int getCustomerCount();
}
