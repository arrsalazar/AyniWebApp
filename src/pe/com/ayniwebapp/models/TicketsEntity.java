package pe.com.ayniwebapp.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * Created by Anthony Reyes on 19/10/2016.
 */
public class TicketsEntity extends BaseEntity {
    private String DEFAULT_QUERY ="SELECT * FROM tickets";

    public  Ticket findByCriteria(String criteria){
        String sql = DEFAULT_QUERY + criteria;
        Ticket ticket=null;
        try{
            ResultSet resultSet=getConnection().createStatement().executeQuery(sql);
            if(resultSet.next()){
                ticket = Ticket.buildFromResultSet(resultSet);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return ticket;
    }
    public Ticket findById(int id){return findByCriteria("WHERE id='"+Integer.toString(id)+"'");}
    public Ticket findByStatus(String status){return  findByCriteria("WHERE status='"+status+"'");}

    public List<Ticket> findByUser(User user){
        List<Ticket> tickets =null;
        tickets = findAllByCriteria("WHERE user_id="+Integer.toString(user.getId()));
        if(tickets != null && tickets.size()>0){
            for (Ticket ticket:tickets){
                ticket.setUser(user);
            }
        }
        return tickets;

    }
    public List<Ticket>findAll(){return findAllByCriteria("");}
    public List<Ticket>findAllOrderedByState(){return findAllByCriteria("ORDER BY state");}

    public List<Ticket>findAllByCriteria(String criteria){
        String sql =DEFAULT_QUERY +" "+criteria;
        List<Ticket> tickets=null;
        try{
            ResultSet resultSet=getConnection().createStatement().executeQuery(sql);
            tickets = new ArrayList<>();
            while (resultSet.next()){
                Ticket ticket = Ticket.buildFromResultSet(resultSet);
                tickets.add(ticket);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return tickets;
    }

    public boolean create(User user, String subject, String description, Date createdAt, Date closedAt, String status) {
        String sql = "INSERT INTO tickets(user_id,subject,description,created_at,closed_ad,status)"
                + "VALUES('"+Integer.toString(user.getId())+"','" + subject + "','" + description + "','" + createdAt + "','" + closedAt + status + "')";
        return modifyWithCriteria(sql);
    }

    public boolean update(int id,User user, String subject, String description, Date createdAt, Date closeAt, String status) {
        String sql = "UPDATE tickets SET user_id='"+Integer.toString(user.getId())+"',subject = '" + subject + "', description = '" + description + "', created_at ='" + createdAt + "', closed_at ='" + closeAt + "', status ='" + status + "'" +
                "WHERE id = " + Integer.toString(id);
        return modifyWithCriteria(sql);
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM tickets WHERE id = '" + Integer.toString(id) + "'";
        return modifyWithCriteria(sql);
    }
}
