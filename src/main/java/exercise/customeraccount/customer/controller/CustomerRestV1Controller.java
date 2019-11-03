package exercise.customeraccount.customer.controller;

import exercise.customeraccount.customer.model.Customer;
import exercise.customeraccount.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
public class CustomerRestV1Controller {

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/test")
    public String test() {
        return "Test";
    }

    @RequestMapping(value="",method = RequestMethod.GET)
    public List<Customer> getCustomers(){
        return customerService.getCustomers();
    }

    @RequestMapping(value="/@count",method = RequestMethod.GET)
    public int getCustomersCount(){
        return customerService.getCustomers().size();
    }

    @RequestMapping(value="/{customerID}",method = RequestMethod.GET)
    public Customer get(@PathVariable String customerID){
        return customerService.get(customerID);
    }

}
