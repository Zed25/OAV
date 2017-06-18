package DAO;

import enumerations.ConnectionType;
import model.Agency;

import java.sql.*;
import java.util.List;

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

    public int getTableEntryNumber(String tableName) {

        String query = "SELECT COUNT(*) FROM " + tableName + ";";

        Connection connection = connect(ConnectionType.SINGLEQUERY);

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            int rowCount = resultSet.getInt("count");
            resultSet.close();
            statement.close();
            disconnect(connection);
            return rowCount;
        } catch (SQLException e) {
            e.printStackTrace();
            disconnect(connection);
            return 0;
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
