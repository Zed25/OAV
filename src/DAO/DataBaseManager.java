package DAO;

import com.sun.rowset.CachedRowSetImpl;
import sun.swing.BakedArrayList;

import javax.sql.rowset.CachedRowSet;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by simone on 29/03/17.
 */
public class DataBaseManager {

    private final String DB_NAME = "jdbc:postgresql://localhost:5432/OAVDB";
    private final String DB_PSW = "postgres";
    private final String DB_USER = "root";
    private static DataBaseManager dbInstance=null;

    private DataBaseManager() {
    }

    public CachedRowSetImpl dbQuery(String simpleSQLQuery){
        Statement statement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        CachedRowSetImpl cachedRowSetImpl = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(DB_NAME, DB_PSW, DB_USER);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(simpleSQLQuery);

            //create a copy of the resultset and return it
            cachedRowSetImpl = new CachedRowSetImpl();
            cachedRowSetImpl.populate(resultSet);

            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return cachedRowSetImpl;
    }

    public static synchronized DataBaseManager getInstance() {
        if (DataBaseManager.dbInstance==null){
            dbInstance = new DataBaseManager();
        }
        return dbInstance;
    }
}
