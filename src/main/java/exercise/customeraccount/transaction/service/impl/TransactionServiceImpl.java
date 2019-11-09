package exercise.customeraccount.transaction.service.impl;

import exercise.customeraccount.transaction.model.Transaction;
import exercise.customeraccount.transaction.repository.TransactionRepository;
import exercise.customeraccount.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;

/*
Transaction Service Implementation
 */
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;


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
        return transactionRepository.save(t);
    }

    @Override
    public boolean deleteTransaction(String accountID) {
        return transactionRepository.deleteByID(accountID);
    }
}
