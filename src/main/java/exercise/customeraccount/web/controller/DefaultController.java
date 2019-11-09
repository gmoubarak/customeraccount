package exercise.customeraccount.web.controller;

import exercise.customeraccount.account.service.AccountService;
import exercise.customeraccount.account.service.AccountServiceException;
import exercise.customeraccount.customer.model.Customer;
import exercise.customeraccount.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class DefaultController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private AccountService accountService;


    @GetMapping(value="/")
    public String homePage(Model model){
        model.addAttribute("pageName","home");
        model.addAttribute("customers",customerService.getCustomers());
        return "/home";
    }
    @GetMapping(value="/customer/{customerID}")
    public String customerPage(Model model, @PathVariable String customerID){
        Customer c=customerService.get(customerID);
        if(c!=null) {
            model.addAttribute("name", c.getName());
            model.addAttribute("surname", c.getSurname());
            model.addAttribute("customerID", customerID);
            try {
                model.addAttribute("accounts", accountService.getAccountsForCustomer(c.getCustomerID()));
            }catch (AccountServiceException e){
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Accounts couldn't not be retrieved ",e);
            }
        }else{
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Customer Not Found");
        }
        return "/customer";
    }
    @GetMapping(value="/customer/{customerID}/reloadAccounts")
    public String reloadAccountsList(Model model, @PathVariable String customerID){
        Customer c=customerService.get(customerID);
        if(c!=null) {
            try{
                model.addAttribute("accounts",accountService.getAccountsForCustomer(c.getCustomerID()));
            }catch (AccountServiceException e){
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Accounts couldn't not be retrieved ",e);
            }
        }
        return "/customer :: #accountsTable";
    }
}
