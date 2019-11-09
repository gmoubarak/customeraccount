package exercise.customeraccount.transaction.service;

import exercise.customeraccount.transaction.model.Transaction;

import java.util.List;

/*
Transaction Service Interface to be called by Account REST controller and other REST controllers
*/
public interface TransactionService {
    //returns the count of all the transactions
    int getTransactionsCount() throws TransactionServiceException;
    //returns the count of all the transactions for an account
    int getTransactionsCountForAccount(String accountID) throws TransactionServiceException;
    //returns the list of transactions for a specific account
    List<Transaction> getTransactionsForAccount(String accountID) throws TransactionServiceException;
    //creates a transaction for a specific account.
    Transaction createTransaction(String accountID,String description, double amount) throws TransactionServiceException;
    //deletes a transaction.
    boolean deleteTransaction(String transactionID) throws TransactionServiceException;
    //returns balance for an account by returing the sum of all its transactions' amounts
    double getBalanceForAccount(String accountID) throws TransactionServiceException;
}
