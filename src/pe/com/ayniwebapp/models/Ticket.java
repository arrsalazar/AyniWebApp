package pe.com.ayniwebapp.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by Anthony Reyes on 19/10/2016.
 */
public class Ticket {
    private int id;
    private User user;
    private String subject;
    private String description;
    private Date createdAt;
    private Date closedAt;
    private String status;

    public Ticket(int id, User user, String subject, String description, Date createdAt, Date closedAt, String status) {
        this.id = id;
        this.user = user;
        this.subject = subject;
        this.description = description;
        this.createdAt = createdAt;
        this.closedAt = closedAt;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getClosedAt() {
        return closedAt;
    }

    public void setClosedAt(Date closedAt) {
        this.closedAt = closedAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static Ticket buildFromResultSet(ResultSet ticketResultSet){
        Ticket ticket=null;
        try{
            ticket = new Ticket(ticketResultSet.getInt("id"),
                    null,
                    ticketResultSet.getString("subject"),
                    ticketResultSet.getString("description"),
                    ticketResultSet.getDate("created_at"),
                    ticketResultSet.getDate("closed_at"),
                    ticketResultSet.getString("status")
                    );

        }catch (SQLException e){
            e.printStackTrace();
        }
        return ticket;
    }


}
