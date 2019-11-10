package exercise.customeraccount.account;

import exercise.customeraccount.account.model.Account;
import exercise.customeraccount.account.service.AccountService;
import exercise.customeraccount.customer.model.Customer;
import exercise.customeraccount.customer.service.CustomerService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
/*
Account Service Tests
 */
@SpringBootTest
public class AccountTests {
    @Autowired
    protected AccountService accountService;
    @Autowired
    protected CustomerService customerService;

    private String accountDesc = "This is a Test Account";
    /*
    Test creation of account but retrieving the first customer and creating a specific account for him/her
    then check if the account created matches the customer id and the account description. balance will be tested in another module.
     */
    @Test
    public Account createAccount() throws Exception{
        Customer c = customerService.getCustomers().get(0);
        Account ac = accountService.createAccount(c.getCustomerID(), accountDesc, 0.0);
        assertEquals(ac.getCustomerID(), c.getCustomerID());
        assertEquals(ac.getDescription(), accountDesc);
        //balance will be asserted later in transactions
        return ac;
    }
    /*
    After account being created we check that the list of accounts is 1 and the list of accounts for that customer is 1 and that the account list returns one item
    that is equal to the created one.
     */
    @Test
    public void getAccount()throws Exception{
        Account ac= createAccount();
        String accountID=ac.getAccountID();
        assertEquals(accountService.getAccountsCount(),1);
        assertEquals(accountService.getAccountsCountForCustomer(ac.getCustomerID()),1);
        List<Account> list=accountService.getAccountsForCustomer(ac.getCustomerID());
        assertEquals(list.size(),1);
        assertEquals(list.get(0).getAccountID(),accountID);
    }
    /*
    after we create an account we delete it by id and then make sure after deletion that the return is null.
     */
    @Test
    public void deleteAccount() throws Exception{
        Account ac= createAccount();
        String accountID=ac.getAccountID();
        accountService.deleteAccount(accountID);
        ac=accountService.getAccount(accountID);
        assertEquals(ac, null);
    }
}
