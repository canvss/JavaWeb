package canvs.dao;


import canvs.pojo.Customer;

import java.util.List;

public interface CustomerDAO {
    void insertCustomer(Customer cust);
    List<Customer> getCustomerList();
    Customer getCustomerById(Integer id);
    void updateCustomerById(Customer cust);
    void delCustomerById(Integer id);
    int getCustomerCount();
}
