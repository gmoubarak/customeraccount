package exercise.customeraccount.account.controller;

import exercise.customeraccount.account.model.Account;
import exercise.customeraccount.account.service.AccountService;
import exercise.customeraccount.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/account")
public class AccountRestV1Controller {

    @Autowired
    private AccountService accountService;
    @Autowired
    private TransactionService transactionService;

    //returns the count of all the accounts
    @RequestMapping(value="/@count",method = RequestMethod.GET)
    public int getAccountsCount(){
        return accountService.getAccountsCount();
    }

    //creates a new account for a customer
    @RequestMapping(value="",method = RequestMethod.POST)
    public Account createAccount(@RequestParam String customerID, @RequestParam String description, @RequestParam Double initialValue){
        return accountService.createAccount(customerID,description,initialValue);
    }

    //deletes an account by id
    @RequestMapping(value="",method = RequestMethod.DELETE)
    public boolean deleteAccount(@RequestParam String accountID){
        return accountService.deleteAccount(accountID);
    }

    //retuns the sum of transactions amount of an account
    @RequestMapping(value="/{accountID}/@balance",method = RequestMethod.GET)
    public double getAmountForAccount(@PathVariable String accountID){
        return transactionService.getBalanceForAccount(accountID);
    }
}
