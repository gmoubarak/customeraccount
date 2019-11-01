package exercise.customeraccount.customer.repository.impl.inmem;

import exercise.customeraccount.customer.model.Customer;
import exercise.customeraccount.customer.repository.CustomerRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class CustomerRepositoryInMem implements CustomerRepository {

    private HashMap<String,Customer>customerMap;

    public CustomerRepositoryInMem(){
        customerMap=new HashMap<String,Customer>();
        int index=0;
        addCustomer(index++,"Liu","Kang");
        addCustomer(index++,"Johnny","Cage");
        addCustomer(index++,"Kung","Lao");
        addCustomer(index++,"Sonya","Blade");
        addCustomer(index++,"Shang","Tsung");
    }
    private void addCustomer(int index,String name,String surname){
        Customer c=new Customer();
        c.setCustomerID("User"+index);
        c.setName(name);
        c.setSurname(surname);
        customerMap.put(c.getCustomerID(),c);
    }
    @Override
    public List<Customer> getCustomers() {
        return new ArrayList<Customer>(customerMap.values());
    }

    @Override
    public Customer get(String customerID) {
        return customerMap.get(customerID);
    }
}
