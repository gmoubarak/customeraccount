package exercise.customeraccount.transaction.model;

import java.sql.Timestamp;

/*
Transaction Model
 */
public class Transaction {

    //Generated transaction id
    private String transactionID;
    //Account id
    private String accountID;
    //Transaction reason
    private String description;
    //transaction amount
    private double amount;
    //Creation timestamp
    private Timestamp createdTimestamp;

    public Timestamp getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(Timestamp createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
