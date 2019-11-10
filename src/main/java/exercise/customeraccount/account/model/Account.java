package exercise.customeraccount.account.model;

import java.sql.Timestamp;

/*
Account Entity
 */
public class Account {

    //Account id
    private String accountID;
    //Customer ID
    private String customerID;
    //Account description
    private String description;
    //Account balance.
    private double balance;
    //Creation timestamp
    private Timestamp createdTimestamp;

    public Timestamp getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(Timestamp createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
