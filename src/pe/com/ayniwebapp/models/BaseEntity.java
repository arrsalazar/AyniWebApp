package pe.com.ayniwebapp.models;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Anthony Reyes on 19/10/2016.
 */
public class BaseEntity {
    private Connection connection;
    public Connection getConnection(){
        return connection;
    }
    public void setConnection(Connection connection){
        this.connection=connection;
    }

    protected boolean modifyWithCriteria(String dml){
        try{
            int result = getConnection().createStatement().executeUpdate(dml);
            return (result>0);

        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

}
