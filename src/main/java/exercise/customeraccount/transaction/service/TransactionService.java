package exercise.customeraccount.transaction.service;

import exercise.customeraccount.transaction.model.Transaction;

import java.util.List;

/*
Transaction Service Interface to be called by Account REST controller and other REST controllers
*/
public interface TransactionService {
    /*
    * @return the count of all the transactions
     */
    int getTransactionsCount() throws TransactionServiceException;
    /*
    * @param accountID id of the desired account transactions
    * @return the count of all the transactions for an account
     */
    int getTransactionsCountForAccount(String accountID) throws TransactionServiceException;
    /*
    * @param accountID id of the desired account transactions
    * @return the list of transactions for a specific account
     */
    List<Transaction> getTransactionsForAccount(String accountID) throws TransactionServiceException;
    /*
    * creates a transaction for a specific account.
    * @param accountID id of the account to which the transactions to be added.
    * @param description of the transaction
    * @param amount is the amount executed in the transaction.
    * @return the newly created transaction.
     */
    Transaction createTransaction(String accountID,String description, double amount) throws TransactionServiceException;
    /*
    * deletes a transaction.
    * @param transactionID id of transaction to be deleted.
    * @return true if the deletion is successful
    *
     */
    boolean deleteTransaction(String transactionID) throws TransactionServiceException;
}
