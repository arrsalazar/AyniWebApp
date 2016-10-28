package pe.com.ayniwebapp.models;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Anthony Reyes on 19/10/2016.
 */
public class Customer {
    private int id;
    private Account account;
    private String firstName;
    private String lastName;
    private String business;
    private String userName;
    private String password;
    private String phoneNumber;
    private String email;
    private String status;

    public Customer(int id, Account account, String firstName, String lastName, String business, String userName, String password, String phoneNumber, String email, String status) {
        this.id = id;
        this.account = account;
        this.firstName = firstName;
        this.lastName = lastName;
        this.business = business;
        this.userName = userName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.status = status;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static Customer buildFromResultSet(ResultSet customerResultSet){
        Customer customer=null;
        try{
            customer = new Customer(customerResultSet.getInt("id"),
                    null,
                    customerResultSet.getString("first_name"),
                    customerResultSet.getString("last_name"),
                    customerResultSet.getString("business"),
                    customerResultSet.getString("user_name"),
                    customerResultSet.getString("password"),
                    customerResultSet.getString("phone_number"),
                    customerResultSet.getString("email"),
                    customerResultSet.getString("status")
                    );

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }


}
