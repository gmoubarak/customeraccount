package exercise.customeraccount.transaction.service.impl;

import exercise.customeraccount.account.service.AccountService;
import exercise.customeraccount.transaction.model.Transaction;
import exercise.customeraccount.transaction.repository.TransactionRepository;
import exercise.customeraccount.transaction.service.TransactionService;
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
    public int getTransactionsCount() {
        return transactionRepository.count();
    }

    @Override
    public int getTransactionsCountForAccount(String accountID) {
        HashMap<String,Object> cr=createCriteria(accountID);
        return transactionRepository.count(cr);
    }
    private HashMap<String,Object> createCriteria(String accountID){
        HashMap<String,Object>cr=new HashMap<String,Object>();
        cr.put("accountID",accountID);
        return cr;
    }

    @Override
    public List<Transaction> getTransactionsForAccount(String accountID) {
        HashMap<String,Object> cr=createCriteria(accountID);
        return transactionRepository.select(cr);
    }

    @Override
    public Transaction createTransaction(String accountID, String description, double amount) {
        Transaction t=new Transaction();
        t.setAccountID(accountID);
        t.setAmount(amount);
        t.setDescription(description);
        t= transactionRepository.save(t);
        return t;
    }

    @Override
    public boolean deleteTransaction(String accountID) {
        return transactionRepository.deleteByID(accountID);
    }

    @Override
    public double getBalanceForAccount(String accountID){
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
