package exercise.customeraccount.account.controller;

import exercise.customeraccount.account.model.Account;
import exercise.customeraccount.account.service.AccountService;
//import exercise.customeraccount.transaction.service.TransactionService;
import exercise.customeraccount.account.service.AccountServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("api/v1/account")
public class AccountRestV1Controller {

    @Autowired
    private AccountService accountService;
//    @Autowired
//    private TransactionService transactionService;

    //returns the count of all the accounts
    @RequestMapping(value="/@count",method = RequestMethod.GET)
    public int getAccountsCount(){
        try{
            return accountService.getAccountsCount();
        }catch (AccountServiceException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Accounts Service couldn't be reached ",e);
        }
    }

    //creates a new account for a customer
    @RequestMapping(value="",method = RequestMethod.POST)
    public Account createAccount(@RequestParam String customerID, @RequestParam String description, @RequestParam Double initialValue){
        try{
            return accountService.createAccount(customerID,description,initialValue);
        }catch (AccountServiceException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Account can't be created ",e);
        }
    }

    //deletes an account by id
    @RequestMapping(value="",method = RequestMethod.DELETE)
    public boolean deleteAccount(@RequestParam String accountID){
        try{
            return accountService.deleteAccount(accountID);
        }catch (AccountServiceException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Account couldn't be deleted ",e);
        }
    }
//
//    //retuns the sum of transactions amount of an account
//    @RequestMapping(value="/{accountID}/@balance",method = RequestMethod.GET)
//    public double getAmountForAccount(@PathVariable String accountID){
//        return transactionService.getBalanceForAccount(accountID);
//    }
}
