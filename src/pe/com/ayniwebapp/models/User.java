package pe.com.ayniwebapp.models;

import javax.jws.soap.SOAPBinding;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Anthony Reyes on 19/10/2016.
 */
public class User {
    private int id;
    private Account account;
    private Customer customer;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String phoneNumber;
    private String email;
    private String status;

    public User(int id, Account account, Customer customer, String firstName, String lastName, String userName, String password, String phoneNumber, String email, String status) {
        this.id = id;
        this.account = account;
        this.customer = customer;
        this.firstName = firstName;
        this.lastName = lastName;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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

    public static User buildFromResultSet (ResultSet userResulSet){
        User user=null;
        try{
            user=new User(
              userResulSet.getInt("id"),null,null,
                    userResulSet.getString("first_name"),
                    userResulSet.getString("last_name"),
                    userResulSet.getString("user_name"),
                    userResulSet.getString("password"),
                    userResulSet.getString("phone_number"),
                    userResulSet.getString("email"),
                    userResulSet.getString("status")
            );

        }catch (SQLException e){
            e.printStackTrace();
        }
        return user;

    }
}
