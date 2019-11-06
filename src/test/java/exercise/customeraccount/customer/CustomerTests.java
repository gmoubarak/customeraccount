package exercise.customeraccount.customer;

import exercise.customeraccount.customer.model.Customer;
import exercise.customeraccount.customer.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
public class CustomerTests {
    @Autowired
    private CustomerService customerService;
    @Test
    public void testCustomers(){
        List<Customer> custs=customerService.getCustomers();
        Iterator<Customer>it=custs.iterator();
        while(it.hasNext()){
            Customer c=it.next();
            assertEquals(c,customerService.get(c.getCustomerID()));
        }
    }
}
