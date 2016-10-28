package pe.com.ayniwebapp.services;

import pe.com.ayniwebapp.models.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.sql.Connection;
import java.util.Date;
import java.util.List;

/**
 * Created by Anthony Reyes on 19/10/2016.
 */
public class AYNIService {

    AccountsEntity accountsEntity = new AccountsEntity();
    CustomersEntity customersEntity =new CustomersEntity();
    TicketsEntity ticketsEntity =new TicketsEntity();
    UsersEntity usersEntity =new UsersEntity();
    Connection connection;

    public Connection getConnection(){
        if(connection == null){
            try{
                InitialContext ctx= new InitialContext();
                Connection connection=(Connection)ctx.lookup("jdbc/MySQLDataSource");
                return connection;
            }catch (NamingException e){
                e.printStackTrace();
                return null;
            }


        }
        else {
            return connection;
        }
    }

    private AccountsEntity getAccountsEntity(){
        if(connection !=null){
            accountsEntity.setConnection(connection);
            return accountsEntity;
        }else return null;
    }

    private CustomersEntity getCustomersEntity(){
        if(connection !=null){
            customersEntity.setConnection(connection);
            return customersEntity;
        }else return null;
    }

    private TicketsEntity getTicketsEntity(){
        if(connection !=null){
            ticketsEntity.setConnection(connection);
            return ticketsEntity;
        }else return null;
    }

   private UsersEntity getUsersEntity(){
        if(connection !=null){
            usersEntity.setConnection(connection);
            return usersEntity;
        }else return null;
    }

    public List<Account> getAccount() {
        return getAccountsEntity().findAll();
    }
    public List<Customer>getCustomer(){return getCustomersEntity().findAll(); }
    public List<Ticket>getTicket(){return getTicketsEntity().findAll();}
    public List<User>getUser(){return getUsersEntity().findAll();}

    public List<Account> getAccountById(int id) {
        return (List<Account>) getAccountsEntity().findById(id);
    }
    public List<Customer>getCustomerById(int id){return (List<Customer>) getCustomersEntity().findById(id); }
    public List<Ticket>getTicketById(int id){return (List<Ticket>) getTicketsEntity().findById(id)  ;  }
    public List<User>getUserById(int id){return (List<User>) getUsersEntity().findById(id)  ;  }

    public List<Customer>getCustomerByName(String name){return  (List<Customer>) getCustomersEntity().findByName(name);}
    public List<Ticket>getTicketByStatus(String status){return  (List<Ticket>) getTicketsEntity().findByStatus(status);}
    public List<User>getUsertByName(String name){return  (List<User>) getUsersEntity().findByName(name);}

    public List<Account> getAccountOrderedByName() {
        return (List<Account>) getAccountsEntity().findAllOrderByName();
    }
    public List<Customer>getCustomerOrderedByName(){return (List<Customer>) getCustomersEntity().findAllOrderedByName();}
    public List<Ticket>getTicketOrderedByState(){return (List<Ticket>) getTicketsEntity().findAllOrderedByState();}
    public List<User>getUserOrderedByName(){return (List<User>) getUsersEntity().findAllOrderedByName();}



    public List<Customer>getCustomerByAccount(Account account){return getCustomersEntity().finByAccount(account);}
    public List<Ticket>getTicketByUser(User user){return getTicketsEntity().findByUser(user);}
    public List<User>getUsertByAccount(Account account){return getUsersEntity().findByAccount(account);}
    public List<User>getUserByCustomer(Customer customer){return getUsersEntity().findByCustomer(customer);}

    public boolean createAccount( String name, String lastName, String business, String userName, String password, String phoneNumber, String email, String state){
        return getAccountsEntity().create(name,lastName,business,userName,password,phoneNumber,email,state);
    }

    public boolean createCustomer( Account account,String name, String lastName, String business, String userName, String password, String phoneNumber, String email, String state){
        return getCustomersEntity().create(account,name,lastName,business,userName,password,phoneNumber,email,state);
    }

    public boolean createTicket(User user, String affair, String description, Date creationDate, Date attentionDate, String state){
        return getTicketsEntity().create(user,affair,description,creationDate,attentionDate,state);
    }

    public boolean createUser( Account account,Customer customer,String name, String lastName,  String userName, String password, String phoneNumber, String email, String state){
        return getUsersEntity().create(account,customer,name,lastName,userName,password,phoneNumber,email,state);
    }



    public boolean updateAccount( int id,String name, String lastName, String business, String userName, String password, String phoneNumber, String email, String state){
        return getAccountsEntity().update(id,name,lastName,business,userName,password,phoneNumber,email,state);
    }


    public boolean updateCustomer( int id,Account account,String name, String lastName, String business, String userName, String password, String phoneNumber, String email, String state){
        return getCustomersEntity().update(id,account,name,lastName,business,userName,password,phoneNumber,email,state);
    }

    public boolean updateTicket(int id,User user, String affair, String description, Date creationDate, Date attentionDate, String state){
        return getTicketsEntity().update(id,user,affair,description,creationDate,attentionDate,state);
    }

    public boolean updateUser( int id,Account account,Customer customer,String name, String lastName, String userName, String password, String phoneNumber, String email, String state){
        return getUsersEntity().update(id,account,customer,name,lastName,userName,password,phoneNumber,email,state);
    }
    public boolean deleteAccount( int id){
        return getAccountsEntity().delete(id);
    }

    public boolean deleteCustomer( int id){
        return getCustomersEntity().delete(id);
    }

    public boolean deleteTicket( int id){return getTicketsEntity().delete(id);}

    public boolean deleteUser( int id){return getUsersEntity().delete(id);}
}
