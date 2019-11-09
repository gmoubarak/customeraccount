package exercise.customeraccount.account.service;

import exercise.customeraccount.account.model.Account;

import java.util.List;

/*
Account Service Interface to be called by Account REST controller
 */
public interface AccountService {
    /*
    * @param id of the desired account
    * @return account by id.
    */
    Account getAccount(String accountID) throws AccountServiceException;
    /*
    *
    * @return the count of all the accounts
     */
    int getAccountsCount() throws AccountServiceException;
    /*
    * @param customerID id of the customer whose accounts to be returned.
    * @return the count of all the accounts
     */
    int getAccountsCountForCustomer(String customerID) throws AccountServiceException;
    /*
    * @param customerID id of the customer whose accounts count to be returned.
    * @return the list of account count for a specific customer
     */
    List<Account>getAccountsForCustomer(String customerID) throws AccountServiceException;
    /*
    * creates and account for a specific customer with an initial value.
    * @param customerID the customer id to whom the account belongs
    * @param description is the account description.
    * @param initialValue if not 0 a transaction is automatically created for that newly created account as initial transaction
    * @return the created account.
     */
    Account createAccount(String customerID,String description, Double initialValue) throws AccountServiceException;
    /*
    * deletes an account.
    * @param accountid is the id of the account to be deleted
    * @return true if account is successfully deleted. false otherwise.
     */
    boolean deleteAccount(String accountID) throws AccountServiceException;
    /*
    * sets account balance.
    * @param accountID is the id of the account to which we want to change the balance.
    * @param newBalance is the desired new balance to be changed for that account.
     */
    void setAccountBalance(String accountID,double newBalance)throws AccountServiceException;
}
