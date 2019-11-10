package exercise.customeraccount.transaction;

import exercise.customeraccount.account.AccountTests;
import exercise.customeraccount.account.model.Account;
import exercise.customeraccount.account.service.AccountService;
import exercise.customeraccount.customer.service.CustomerService;
import exercise.customeraccount.transaction.model.Transaction;
import exercise.customeraccount.transaction.service.TransactionService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/*
Transaction Tests
 */
@SpringBootTest
public class TransactionTests extends AccountTests {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private TransactionService transactionService;
    private String transaction1="TestTransaction1";
    private String transaction2="TestTransaction2";
    @Test
    public void testTransactionLifeCycle() throws Exception{
        Account ac=createAccount();
        Transaction t1=transactionService.createTransaction(ac.getAccountID(),transaction1,300);
        Transaction t2=transactionService.createTransaction(ac.getAccountID(),transaction2,500);
        ac=accountService.getAccount(ac.getAccountID());
        assertEquals(ac.getBalance(),800.0);
        transactionService.deleteTransaction(t1.getTransactionID());
        ac=accountService.getAccount(ac.getAccountID());
        assertEquals(ac.getBalance(),500.0);
        transactionService.deleteTransaction(t2.getTransactionID());
        ac=accountService.getAccount(ac.getAccountID());
        assertEquals(ac.getBalance(),0.0);

    }
}
