package exercise.customeraccount.transaction.repository.impl.inmem;

import exercise.customeraccount.repository.inmemory.InMemoryRepository;
import exercise.customeraccount.transaction.model.Transaction;
import exercise.customeraccount.transaction.repository.TransactionRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.function.Supplier;
/*
In Memory implementation of TransactionRepsitory
 */

@Repository
public class TransactionRepositoryInMem extends InMemoryRepository<Transaction,String> implements TransactionRepository {
    private Supplier<String> supplier;

    public TransactionRepositoryInMem(){
        supplier=()->{return "Account"+System.currentTimeMillis();};
    }
    public Supplier<String> getSupplier(){
        return supplier;
    }

    //retrieves the ID from entity T
    public String getID(Transaction t){
        return t.getTransactionID();
    }

    //sets the ID in  entity T
    public void setID(Transaction t,String id){
        t.setTransactionID(id);
    }

    //Checks if Entity satisfies criteria
    public boolean filter(Transaction t, Map<String, Object> criteria){
        Object accountID=criteria.get("accountID");
        return accountID!=null && t.getAccountID().equals(accountID);
    }
}
