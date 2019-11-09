package exercise.customeraccount.customer.model;
/*
Customer Entity
 */
public class Customer {
    //Customer ID
    private String customerID;
    //Customer's name
    private String name;
    //Customer's surname.
    private String surname;

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

}
