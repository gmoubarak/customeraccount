package exercise.customeraccount.web.controller;

import exercise.customeraccount.account.model.Account;
import exercise.customeraccount.account.service.AccountService;
import exercise.customeraccount.customer.model.Customer;
import exercise.customeraccount.customer.service.CustomerService;
import exercise.customeraccount.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class TransactionController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private TransactionService transactionService;

    //loading model for Transactions info of an account.
    @GetMapping(value="/account/{accountID}")
    public String accountPage(Model model, @PathVariable String accountID){
//        model.addAttribute("pageName","transactions");
//        Account ac=accountService.getAccount(accountID);
//        if(ac!=null) {
//            Customer c=customerService.get(ac.getCustomerID());
//            model.addAttribute("name", c.getName());
//            model.addAttribute("surname", c.getSurname());
//            model.addAttribute("accountID", ac.getAccountID());
//            model.addAttribute("accountDescription", ac.getDescription());
//            model.addAttribute("transaction",transactionService.getTransactionsCountForAccount(ac.getAccountID()));
//        }
        return "/transactions";
    }
    //Ajax call to reload the transactions table.
    @GetMapping(value="/account/{accountID}/reloadTransactions")
    public String reloadTransactionsList(Model model, @PathVariable String accountID){
//        Account ac=accountService.getAccount(accountID);
//        if(ac!=null) {
//            model.addAttribute("transactions",accountService.getAccountsForCustomer(c.getCustomerID()));
//        }
        return "/transactions :: #transactionsTable";
    }

}
