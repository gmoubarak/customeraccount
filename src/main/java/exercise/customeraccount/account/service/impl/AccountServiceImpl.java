package exercise.customeraccount.account.service.impl;

import exercise.customeraccount.account.model.Account;
import exercise.customeraccount.account.repository.AccountRepository;
import exercise.customeraccount.account.service.AccountService;
import exercise.customeraccount.account.service.AccountServiceException;
import exercise.customeraccount.repository.RepositoryException;
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

    //returns an account by its ID.
    @Override
    public Account getAccount(String accountID) throws AccountServiceException {
        try {
            return accountRepository.getByID(accountID);
        }catch(RepositoryException e){
            throw new AccountServiceException(e.getMessage());
        }
    }
    //returns the count of all the accounts
    @Override
    public int getAccountsCount() throws AccountServiceException{
        try{
            return accountRepository.count();
        }catch(RepositoryException e){
            throw new AccountServiceException(e.getMessage());
        }
    }

    //returns the count of all the accounts
    @Override
    public int getAccountsCountForCustomer(String customerID) throws AccountServiceException{
        HashMap<String,Object> cr=createCriteria(customerID);
        try{
            return accountRepository.count(cr);
        }catch(RepositoryException e){
            throw new AccountServiceException(e.getMessage());
        }
    }
    private HashMap<String,Object> createCriteria(String customerID) throws AccountServiceException{
        HashMap<String,Object>cr=new HashMap<String,Object>();
        cr.put("customerID",customerID);
        return cr;
    }
    @Override
    public List<Account> getAccountsForCustomer(String customerID)  throws AccountServiceException{
        HashMap<String,Object> cr=createCriteria(customerID);
        try{
            return accountRepository.select(cr);
        }catch(RepositoryException e){
            throw new AccountServiceException(e.getMessage());
        }
    }

    @Override
    public Account createAccount(String customerID, String description, Double initialValue)  throws AccountServiceException{
        Account ac=new Account();
        ac.setCustomerID(customerID);
        ac.setDescription(description);
        try{
            return accountRepository.save(ac);
        }catch(RepositoryException e){
            throw new AccountServiceException(e.getMessage());
        }
    }

    @Override
    public boolean deleteAccount(String accountID)  throws AccountServiceException{
        try{
            return accountRepository.deleteByID(accountID);
        }catch(RepositoryException e){
            throw new AccountServiceException(e.getMessage());
        }
    }
    @Override
    public void setAccountBalance(String accountID,double newBalance)throws AccountServiceException{
        Account ac=getAccount(accountID);
        ac.setBalance(newBalance);
        try{
            accountRepository.save(ac);
        }catch(RepositoryException e){
            throw new AccountServiceException(e.getMessage());
        }
    }
}
