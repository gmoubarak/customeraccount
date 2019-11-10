package exercise.customeraccount.transaction.service.impl;

import exercise.customeraccount.account.model.Account;
import exercise.customeraccount.account.service.AccountService;
import exercise.customeraccount.account.service.AccountServiceException;
import exercise.customeraccount.repository.RepositoryException;
import exercise.customeraccount.transaction.model.Transaction;
import exercise.customeraccount.transaction.repository.TransactionRepository;
import exercise.customeraccount.transaction.service.TransactionService;
import exercise.customeraccount.transaction.service.TransactionServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;

/*
Transaction Service Implementation
 */
@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private AccountService accountService;
    @Autowired
    private TransactionRepository transactionRepository;


    @Override
    public int getTransactionsCount()  throws TransactionServiceException {
        try {
            return transactionRepository.count();
        }catch(RepositoryException e){
            throw new TransactionServiceException(e.getMessage());
        }
    }

    @Override
    public int getTransactionsCountForAccount(String accountID)  throws TransactionServiceException{
        HashMap<String,Object> cr=createCriteria(accountID);
        try{
            return transactionRepository.count(cr);
        }catch(RepositoryException e){
            throw new TransactionServiceException(e.getMessage());
        }
    }
    private HashMap<String,Object> createCriteria(String accountID) throws TransactionServiceException{
        HashMap<String,Object>cr=new HashMap<String,Object>();
        cr.put("accountID",accountID);
        return cr;
    }

    @Override
    public List<Transaction> getTransactionsForAccount(String accountID)  throws TransactionServiceException{
        HashMap<String,Object> cr=createCriteria(accountID);
        try{
            List<Transaction>list= transactionRepository.select(cr);
            if(list==null)return null;
            Collections.sort(list,comp);
            return list;
        }catch(RepositoryException e){
            throw new TransactionServiceException(e.getMessage());
        }
    }
    private TimestampComparator comp=new TimestampComparator();
    class TimestampComparator implements Comparator<Transaction> {

        @Override
        public int compare(Transaction o1, Transaction o2) {
            return Long.compare(o1.getCreatedTimestamp().getTime(),o2.getCreatedTimestamp().getTime());
        }
    }
    @Override
    public Transaction createTransaction(String accountID, String description, double amount)  throws TransactionServiceException{
        if(amount==0){
            return null;
        }
        Transaction t=new Transaction();
        t.setAccountID(accountID);
        t.setAmount(amount);
        t.setDescription(description);
        t.setCreatedTimestamp(Timestamp.from(Instant.now()));
        try{
            t= transactionRepository.save(t);
            Account ac=accountService.getAccount(accountID);
            accountService.setAccountBalance(accountID,ac.getBalance()+amount);
        }catch(RepositoryException e){
            throw new TransactionServiceException(e.getMessage());
        }catch(AccountServiceException ae){
            throw new TransactionServiceException(ae.getMessage());
        }
        return t;
    }

    @Override
    public boolean deleteTransaction(String transactionID)  throws TransactionServiceException{
        try{
            Transaction t= transactionRepository.getByID(transactionID);
            boolean result= transactionRepository.delete(t);
            if(!result)return false;
            Account ac=accountService.getAccount(t.getAccountID());
            accountService.setAccountBalance(t.getAccountID(),ac.getBalance()-t.getAmount());
        }catch(RepositoryException e){
            throw new TransactionServiceException(e.getMessage());
        }catch(AccountServiceException ae){
            throw new TransactionServiceException(ae.getMessage());
        }
        return true;
    }
    @Override
    public boolean deleteTransactionsOfAccount(String accountID)throws TransactionServiceException{
        List<Transaction>list=getTransactionsForAccount(accountID);
        try{
            return transactionRepository.deleteAll(list);
        }catch(RepositoryException e) {
            throw new TransactionServiceException(e.getMessage());
        }
    }


}
