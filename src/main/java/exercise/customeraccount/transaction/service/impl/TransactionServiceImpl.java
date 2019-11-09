package exercise.customeraccount.transaction.service.impl;

import exercise.customeraccount.account.service.AccountService;
import exercise.customeraccount.repository.RepositoryException;
import exercise.customeraccount.transaction.model.Transaction;
import exercise.customeraccount.transaction.repository.TransactionRepository;
import exercise.customeraccount.transaction.service.TransactionService;
import exercise.customeraccount.transaction.service.TransactionServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/*
Transaction Service Implementation
 */
@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountService accountService;


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
            return transactionRepository.select(cr);
        }catch(RepositoryException e){
            throw new TransactionServiceException(e.getMessage());
        }
    }

    @Override
    public Transaction createTransaction(String accountID, String description, double amount)  throws TransactionServiceException{
        Transaction t=new Transaction();
        t.setAccountID(accountID);
        t.setAmount(amount);
        t.setDescription(description);
        try{
            t= transactionRepository.save(t);
        }catch(RepositoryException e){
            throw new TransactionServiceException(e.getMessage());
        }
        return t;
    }

    @Override
    public boolean deleteTransaction(String accountID)  throws TransactionServiceException{
        try{
            return transactionRepository.deleteByID(accountID);
        }catch(RepositoryException e){
            throw new TransactionServiceException(e.getMessage());
        }
    }

    @Override
    public double getBalanceForAccount(String accountID) throws TransactionServiceException{
        List<Transaction>ts=getTransactionsForAccount(accountID);
        Iterator<Transaction>it=ts.iterator();
        double sum=0;
        while(it.hasNext()){
            Transaction t=it.next();
            sum+=t.getAmount();
        }
        return sum;
    }

}
