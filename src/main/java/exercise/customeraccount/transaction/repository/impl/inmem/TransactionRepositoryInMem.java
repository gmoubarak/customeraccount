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
    private int counter=0;
    public TransactionRepositoryInMem(){
        supplier=()->{counter++;
            counter=counter%80;
            StringBuffer sb=new StringBuffer();
            sb.append(System.currentTimeMillis());
            sb.append(counter+10);
            return sb.toString();};
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
