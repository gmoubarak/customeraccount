package exercise.customeraccount.customer.service.impl;

import exercise.customeraccount.customer.model.Customer;
import exercise.customeraccount.customer.repository.CustomerRepository;
import exercise.customeraccount.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> getCustomers() {
        return customerRepository.getCustomers();
    }

    @Override
    public Customer get(String customerID) {
        return customerRepository.get(customerID);
    }
}
