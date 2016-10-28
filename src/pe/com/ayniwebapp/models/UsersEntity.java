package pe.com.ayniwebapp.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anthony Reyes on 19/10/2016.
 */
public class UsersEntity extends BaseEntity{
    private String DEFAULT_QUERY="SELECT * FROM users";

    public User findByCriteria(String criteria){
        String sql=DEFAULT_QUERY + criteria;
        User user=null;
        try{
            ResultSet resultSet=getConnection().createStatement().executeQuery(sql);
            if(resultSet.next()){
                user = User.buildFromResultSet(resultSet);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }

    public User findById(int id){return findByCriteria("WHERE id="+Integer.toString(id));}

    public User findByName(String firstName){return  findByCriteria("WHERE first_name='"+firstName+"'");}

    public List<User> findByAccount(Account account){
        List<User> users=null;
        users=findAllByCriteria("WHERE account_id="+Integer.toString(account.getId()));
        if(users !=null && users.size()>0){
            for(User user:users){
                user.setAccount(account);
            }
        }return users;
    }
    public List<User> findByCustomer(Customer customer){
        List<User> users=null;
        users=findAllByCriteria("WHERE customer_id="+Integer.toString(customer.getId()));
        if(users !=null && users.size()>0){
            for(User user:users){
                user.setCustomer(customer);
            }
        }return users;
    }

    public List<User> findAll() { return findAllByCriteria("");}

    public List<User> findAllOrderedByName() { return findAllByCriteria("ORDER BY first_name");}

    public List<User> findAllByCriteria(String criteria) {
        String sql = DEFAULT_QUERY + " " + criteria;
        List<User> users = null;

        try {
            ResultSet resultSet = getConnection().createStatement().executeQuery(sql);
            users = new ArrayList<>();
            while(resultSet.next()) {
                User user = User.buildFromResultSet(resultSet);
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public boolean create(Account account,Customer customer,String firstName, String lastName, String userName, String password, String phoneNumber, String email, String status) {
        String sql = "INSERT INTO users(account_id,customer_id,first_name,last_name,user_name,password,phone_number,email,status)"
                + "VALUES('"+Integer.toString(account.getId())+"','"+Integer.toString(customer.getId())+ "','"+ firstName + "','" + lastName + "','" + userName + "','" + password + "','" + phoneNumber + "','" + email + "','" + status + "')";
        return modifyWithCriteria(sql);
    }

    public boolean update(int id,Account account,Customer customer, String firstName, String lastName, String userName, String password, String phoneNumber, String email, String status) {
        String sql = "UPDATE users SET account_id='"+Integer.toString(account.getId())+"',customer_id='"+Integer.toString(customer.getId())+"', first_name = '" + firstName + "', last_name = '" + lastName + "',  user_name ='" + userName + "', password ='" + password + "', phone_number ='" + phoneNumber + "', email ='" + email + "', status ='" + status + "'" +
                "WHERE id = " + Integer.toString(id);
        return modifyWithCriteria(sql);
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM users WHERE id = '" + Integer.toString(id) + "'";
        return modifyWithCriteria(sql);
    }
}
