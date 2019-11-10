package exercise.customeraccount.account.service.impl;

import exercise.customeraccount.account.model.Account;
import exercise.customeraccount.account.repository.AccountRepository;
import exercise.customeraccount.account.service.AccountService;
import exercise.customeraccount.account.service.AccountServiceException;
import exercise.customeraccount.repository.RepositoryException;
import exercise.customeraccount.transaction.model.Transaction;
import exercise.customeraccount.transaction.service.TransactionService;
import exercise.customeraccount.transaction.service.TransactionServiceException;
import exercise.customeraccount.transaction.service.impl.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/*
Account Service Implementation
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionService transactionService;

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
            List<Account>list=accountRepository.select(cr);
            if(list==null)return null;
            Collections.sort(list,comp);
            return list;
        }catch(RepositoryException e){
            throw new AccountServiceException(e.getMessage());
        }
    }
    private TimestampComparator comp=new TimestampComparator();
    class TimestampComparator implements Comparator<Account> {

        @Override
        public int compare(Account o1, Account o2) {
            return Long.compare(o1.getCreatedTimestamp().getTime(),o2.getCreatedTimestamp().getTime());
        }
    }

    @Override
    public Account createAccount(String customerID, String description, Double initialValue)  throws AccountServiceException{
        Account ac=new Account();
        ac.setCustomerID(customerID);
        ac.setDescription(description);
        ac.setCreatedTimestamp(Timestamp.from(Instant.now()));
        try{
            ac= accountRepository.save(ac);
            if(initialValue!=0)
                transactionService.createTransaction(ac.getAccountID(),"Initial Transaction",initialValue);
        }catch(RepositoryException e){
            throw new AccountServiceException(e.getMessage());
        }catch(TransactionServiceException te){
            throw new AccountServiceException(te.getMessage());
        }
        return ac;
    }
    /*
    * Once an account is deleted all the related transactions are deleted with it
     */
    @Override
    public boolean deleteAccount(String accountID)  throws AccountServiceException{
        try{
            boolean result= accountRepository.deleteByID(accountID);
            if(result){
                transactionService.deleteTransactionsOfAccount(accountID);
            }
            return result;
        }catch(RepositoryException e){
            throw new AccountServiceException(e.getMessage());
        }catch(TransactionServiceException te){
            throw new AccountServiceException(te.getMessage());
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
