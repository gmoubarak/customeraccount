package exercise.customeraccount.transaction.controller;

import exercise.customeraccount.transaction.model.Transaction;
import exercise.customeraccount.transaction.service.TransactionService;
import exercise.customeraccount.transaction.service.TransactionServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("api/v1/transaction")
public class TransactionRestV1Controller {

    @Autowired
    private TransactionService transactionService;

    //returns the count of all the transactions
    @RequestMapping(value="/@count",method = RequestMethod.GET)
    public int getTransactionsCount(){
        try{
            return transactionService.getTransactionsCount();
        }catch (TransactionServiceException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Transaction Service couldn't be reached ",e);
        }
    }

    //creates a new transaction for an account
    @RequestMapping(value="",method = RequestMethod.POST)
    public Transaction createTransaction(@RequestParam String accountID, @RequestParam String description, @RequestParam Double amount){
        try{
            return transactionService.createTransaction(accountID,description,amount);
        }catch (TransactionServiceException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Transaction Service couldn't be reached ",e);
        }
    }

    //deletes a transaction by id
    @RequestMapping(value="",method = RequestMethod.DELETE)
    public boolean deleteTransaction(@RequestParam String transactionID){
        try{
            return transactionService.deleteTransaction(transactionID);
        }catch (TransactionServiceException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Transaction Service couldn't be reached ",e);
        }
    }
}
