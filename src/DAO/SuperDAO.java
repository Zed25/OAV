package DAO;

import enumerations.ConnectionType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by simone on 06/04/17.
 */
public abstract class SuperDAO {

    private final String DB_NAME = "jdbc:postgresql://localhost:5432/OAVDB";
    private final String DB_PSW = "postgres";
    private final String DB_USER = "root";

    public Connection connect(ConnectionType connectionType){
        Connection connection;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(DB_NAME, DB_PSW, DB_USER);
            if(connectionType == ConnectionType.COMPQUERY) {
                connection.setAutoCommit(false);
                System.out.println("connection without autocommit"); //DEBUG
            }else{
                System.out.println("connection with autocommit"); //DEBUG
            }
            return connection;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void disconnect(Connection connection){
        try {
            connection.close();
            System.out.println("connection close correctly"); //DEBUG
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getDB_NAME() {
        return DB_NAME;
    }

    public String getDB_PSW() {
        return DB_PSW;
    }

    public String getDB_USER() {
        return DB_USER;
    }
}
