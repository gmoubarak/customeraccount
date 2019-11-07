package exercise.customeraccount.account.service.impl;

import exercise.customeraccount.account.model.Account;
import exercise.customeraccount.account.repository.AccountRepository;
import exercise.customeraccount.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/*
Account Service Implementation
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;


    //returns the count of all the accounts
    @Override
    public int getAccountsCount(){
        return accountRepository.count();
    }

    //returns the count of all the accounts
    @Override
    public int getAccountsCountForCustomer(String customerID){
        HashMap<String,Object> cr=createCriteria(customerID);
        return accountRepository.count(cr);
    }
    private HashMap<String,Object> createCriteria(String customerID){
        HashMap<String,Object>cr=new HashMap<String,Object>();
        cr.put("customerID",customerID);
        return cr;
    }
    @Override
    public List<Account> getAccountsForCustomer(String customerID) {
        HashMap<String,Object> cr=createCriteria(customerID);
        return accountRepository.select(cr);
    }

    @Override
    public Account createAccount(String customerID, String description, Double initialValue) {
        Account ac=new Account();
        ac.setCustomerID(customerID);
        ac.setDescription(description);
        return accountRepository.save(ac);
    }

    @Override
    public boolean deleteAccount(String accountID) {
        return accountRepository.deleteByID(accountID);
    }
}
