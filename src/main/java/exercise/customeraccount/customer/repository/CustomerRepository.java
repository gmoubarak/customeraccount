package exercise.customeraccount.customer.repository;

import exercise.customeraccount.customer.model.Customer;

import java.util.List;

public interface CustomerRepository {
    List<Customer>getCustomers();
    Customer get(String customerID);
}
