package pe.com.ayniwebapp.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anthony Reyes on 19/10/2016.
 */
public class AccountsEntity extends BaseEntity {

    private static String DEFAULT_QUERY = "SELECT * FROM accounts";

    public Account findById(int id) {
        String sql = DEFAULT_QUERY + " WHERE id = " + Integer.toString(id);
        try {
            ResultSet resultSet = getConnection().createStatement().executeQuery(sql);
            if (!resultSet.next()) return null;
            Account account = Account.buildFromResultSet(resultSet);
            return account;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<Account> findByCriteria(String criteria) {
        String sql = DEFAULT_QUERY + " " + criteria;
        List<Account> accounts = null;
        try {
            ResultSet resultSet = getConnection().createStatement().executeQuery(sql);
            while (resultSet.next()) {
                if (accounts == null) {
                    accounts = new ArrayList<>();
                    Account account = Account.buildFromResultSet(resultSet);
                    accounts.add(account);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Account> findAll() {
        return findByCriteria("");
    }

    public List<Account> findAllOrderByName() {
        return findByCriteria("ORDER BY first_name");
    }

    public boolean create(String firstName, String lastName, String business, String userName, String password, String phoneNumber, String email, String status) {
        String sql = "INSERT INTO accounts(first_name,last_name,business,user_name,password,phone_number,email,status)"
                + "VALUES('" + firstName + "','" + lastName + "','" + business + "','" + userName + "','" + password + "','" + phoneNumber + "','" + email + "','" + status + "')";
        return modifyWithCriteria(sql);
    }

    public boolean update(int id, String firstName, String lastName, String business, String userName, String password, String phoneNumber, String email, String status) {
        String sql = "UPDATE accounts SET first_name = '" + firstName + "', last_name = '" + lastName + "', business ='" + business + "', user_name ='" + userName + "', password ='" + password + "', phone_number ='" + phoneNumber + "', email ='" + email + "', status ='" + status + "'" +
                "WHERE id = " + Integer.toString(id);
        return modifyWithCriteria(sql);
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM accounts WHERE id = '" + Integer.toString(id) + "'";
        return modifyWithCriteria(sql);
    }
}

