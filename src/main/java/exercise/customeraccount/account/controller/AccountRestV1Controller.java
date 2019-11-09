package exercise.customeraccount.account.controller;

import exercise.customeraccount.account.model.Account;
import exercise.customeraccount.account.service.AccountService;
import exercise.customeraccount.account.service.AccountServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("api/v1/account")
public class AccountRestV1Controller {

    @Autowired
    private AccountService accountService;

    /*
    * @return the count of all the accounts
     */
    @RequestMapping(value="/@count",method = RequestMethod.GET)
    public int getAccountsCount(){
        try{
            return accountService.getAccountsCount();
        }catch (AccountServiceException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Accounts Service couldn't be reached ",e);
        }
    }

    /*
    * creates a new account for a customer
     * @param customerID the customer id to whom the account belongs
     * @param description is the account description.
     * @param initialValue if not 0 a transaction is automatically created for that newly created account as initial transaction
     * @return the created account.
     */
    @RequestMapping(value="",method = RequestMethod.POST)
    public Account createAccount(@RequestParam String customerID, @RequestParam String description, @RequestParam Double initialValue){
        try{
            return accountService.createAccount(customerID,description,initialValue);
        }catch (AccountServiceException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Account can't be created ",e);
        }
    }

    /*
    * deletes an account by id
    * @param accountID id of the account to be deleted
    * @return true if account is deleted successfully. false otherwise.
     */
    @RequestMapping(value="",method = RequestMethod.DELETE)
    public boolean deleteAccount(@RequestParam String accountID){
        try{
            return accountService.deleteAccount(accountID);
        }catch (AccountServiceException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Account couldn't be deleted ",e);
        }
    }


}
