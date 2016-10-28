package pe.com.ayniwebapp.models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anthony Reyes on 19/10/2016.
 */
public class CustomersEntity extends BaseEntity{
    private String DEFAULT_QUERY = "SELECT * FROM customers";

    public Customer findByCriteria(String criteria) {
        String sql = DEFAULT_QUERY + criteria;
        Customer customer = null;

        try {
            ResultSet resultSet = getConnection()
                    .createStatement()
                    .executeQuery(sql);
            if(resultSet.next()) {
                customer = Customer.buildFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;

    }

    public Customer findById(int id){
        return  findByCriteria("WHERE id='"+Integer.toString(id)+"'");
    }

    public Customer findByName(String firstName){
        return  findByCriteria("WHERE first_Name='"+firstName+"'");
    }

    public List<Customer> finByAccount(Account account){
        List<Customer> customers=null;
        customers = findAllByCriteria("WHERE id="+Integer.toString(account.getId()));
        if(customers!=null &&  customers.size()>0){
            for(Customer customer:customers){
                customer.setAccount(account);
            }
        }
        return customers;

    }

    public List<Customer> findAll(){
        return  findAllByCriteria("");
    }

    public List<Customer>findAllOrderedByName(){
        return findAllByCriteria("ORDER BY first_name");
    }

    public List<Customer>findAllByCriteria(String criteria){
        String sql=DEFAULT_QUERY +" "+criteria;
        List<Customer> customers =null;

        try{
            ResultSet resultSet=getConnection().createStatement().executeQuery(sql);
            customers=new ArrayList<>();
            while(resultSet.next()){
                Customer customer=Customer.buildFromResultSet(resultSet);
                customers.add(customer);
            }

        }catch (SQLException e){
            e.printStackTrace();;

        }
        return customers;
    }

    public boolean create(Account account,String firstName, String lastName, String business, String userName, String password, String phoneNumber, String email, String status) {
        String sql = "INSERT INTO customers(account_id,first_name,last_name,business,user_name,password,phone_number,email,status)"
                + "VALUES('"+Integer.toString(account.getId())+"','" + firstName + "','" + lastName + "','" + business + "','" + userName + "','" + password + "','" + phoneNumber + "','" + email + "','" + status + "')";
        return modifyWithCriteria(sql);
    }

    public boolean update(int id,Account account, String firstName, String lastName, String business, String userName, String password, String phoneNumber, String email, String status) {
        String sql = "UPDATE customers SET account_id='"+Integer.toString(account.getId())+"',first_name = '" + firstName + "', last_name = '" + lastName + "', business ='" + business + "', user_name ='" + userName + "', password ='" + password + "', phone_number ='" + phoneNumber + "', email ='" + email + "', status ='" + status + "'" +
                "WHERE id = " + Integer.toString(id);
        return modifyWithCriteria(sql);
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM customers WHERE id = '" + Integer.toString(id) + "'";
        return modifyWithCriteria(sql);
    }

}
