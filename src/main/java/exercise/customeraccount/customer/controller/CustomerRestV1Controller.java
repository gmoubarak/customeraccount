package exercise.customeraccount.customer.controller;

import exercise.customeraccount.account.model.Account;
import exercise.customeraccount.account.service.AccountService;
import exercise.customeraccount.account.service.AccountServiceException;
import exercise.customeraccount.customer.model.Customer;
import exercise.customeraccount.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
public class CustomerRestV1Controller {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AccountService accountService;

    @RequestMapping("/test")
    public String test() {
        return "Test";
    }

    //returns the list of customers
    @RequestMapping(value="",method = RequestMethod.GET)
    public List<Customer> getCustomers(){
        return customerService.getCustomers();
    }

    //returns the count of customer.
    @RequestMapping(value="/@count",method = RequestMethod.GET)
    public int getCustomersCount(){
        return customerService.getCustomers().size();
    }
    //gets customer info by id
    @RequestMapping(value="/{customerID}",method = RequestMethod.GET)
    public Customer getCustomer(@PathVariable String customerID){
        return customerService.get(customerID);
    }

    // Methods implemented after adding the Account service//////
    //gets the accounts list for a customer
    @RequestMapping(value="/{customerID}/accounts",method = RequestMethod.GET)
    public List<Account> getAccountListForCustomer(@PathVariable String customerID){
        try{
            return accountService.getAccountsForCustomer(customerID);
        }catch (AccountServiceException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Accounts couldn't not be retrieved ",e);
        }
    }

    //gets the accounts count for a customer
    @RequestMapping(value="/{customerID}/accounts/@count",method = RequestMethod.GET)
    public int getCustomerAccountsCount(@PathVariable String customerID){
        try{
            return accountService.getAccountsCountForCustomer(customerID);
        }catch (AccountServiceException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Accounts couldn't not be retrieved ",e);
        }
    }
    ////////////////////////////////////////////////////////////////
}
