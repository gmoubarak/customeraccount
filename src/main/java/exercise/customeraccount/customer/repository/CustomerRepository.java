package exercise.customeraccount.customer.repository;

import exercise.customeraccount.customer.model.Customer;

import java.util.List;

public interface CustomerRepository {
    /*
    * @return list of all customers
     */
    List<Customer>getCustomers();
    /*
    * @param customerID is the id of the desired customer
    * @return customer entity.
     */
    Customer get(String customerID);
}
