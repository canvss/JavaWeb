package canvs.customer.dao;


import canvs.customer.pojo.Customer;

import java.util.List;

public interface CustomerDAO {
    int insertCustomer(Customer cust);
    List<Customer> getCustomerList(int pageNumber,int pageCount);
    Customer getCustomerById(Integer id);
    void updateCustomerById(Customer cust);
    void delCustomerById(Integer id);
    int getCustomerCount();
}
