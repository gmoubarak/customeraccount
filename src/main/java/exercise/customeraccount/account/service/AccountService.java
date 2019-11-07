package exercise.customeraccount.account.service;

import exercise.customeraccount.account.model.Account;

import java.util.List;

/*
Account Service Interface to be called by Account REST controller
 */
public interface AccountService {
    //returns the count of all the accounts
    int getAccountsCount();
    //returns the count of all the accounts
    int getAccountsCountForCustomer(String customerID);
    //returns the list of account for a specific customer
    List<Account>getAccountsForCustomer(String customerID);
    //creates and account for a specific customer with an initial value.
    Account createAccount(String customerID,String description, Double initialValue);
    //deletes an account.
    boolean deleteAccount(String accountID);
}
