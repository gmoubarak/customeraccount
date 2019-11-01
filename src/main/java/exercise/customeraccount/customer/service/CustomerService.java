package exercise.customeraccount.customer.service;

import exercise.customeraccount.customer.model.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> getCustomers();
    Customer get(String customerID);
}
