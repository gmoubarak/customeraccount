package exercise.customeraccount.transaction.controller;

import exercise.customeraccount.transaction.model.Transaction;
import exercise.customeraccount.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/transaction")
public class TransactionRestV1Controller {

    @Autowired
    private TransactionService transactionService;

    //returns the count of all the transactions
    @RequestMapping(value="/@count",method = RequestMethod.GET)
    public int getTransactionsCount(){
        return transactionService.getTransactionsCount();
    }

    //creates a new transaction for an account
    @RequestMapping(value="",method = RequestMethod.POST)
    public Transaction createAccount(@RequestParam String accountID, @RequestParam String description, @RequestParam Double amount){
        return transactionService.createTransaction(accountID,description,amount);
    }

    //deletes a transaction by id
    @RequestMapping(value="",method = RequestMethod.DELETE)
    public boolean deleteTransaction(@RequestParam String transactionID){
        return transactionService.deleteTransaction(transactionID);
    }
}
