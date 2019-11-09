package exercise.customeraccount.account.repository.impl.inmemory;

import exercise.customeraccount.account.model.Account;
import exercise.customeraccount.account.repository.AccountRepository;
import exercise.customeraccount.repository.inmemory.InMemoryRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.function.Supplier;
/*
In Memory implementation of AccountRepsitory
 */
@Repository
public class AccountRepositoryInMem extends InMemoryRepository<Account,String> implements AccountRepository {

    private Supplier<String> supplier;

    public AccountRepositoryInMem(){
        supplier=()->{return "Account"+System.currentTimeMillis();};
    }
    public Supplier<String> getSupplier(){
        return supplier;
    }

    //retrieves the ID from entity T.
    //No Need to Throw Exception since it's all in memory
    public String getID(Account account){
        return account.getAccountID();
    }

    //sets the ID in  entity T
    //No Need to Throw Exception since it's all in memory
    public void setID(Account account,String id){
        account.setAccountID(id);
    }

    //Checks if Entity satisfies criteria
    //No Need to Throw Exception since it's all in memory
    public boolean filter(Account account, Map<String, Object> criteria){
        Object customerID=criteria.get("customerID");
        return customerID!=null && account.getCustomerID().equals(customerID);
    }

}
