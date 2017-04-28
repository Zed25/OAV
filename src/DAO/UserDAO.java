package DAO;

import beans.login.AdministrationBean;
import beans.login.UserBean;
import com.sun.rowset.CachedRowSetImpl;
import enumerations.ConnectionType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by simone on 29/03/17.
 */
public class UserDAO extends SuperDAO{

    public boolean login(UserBean user) {

        String query = "SELECT * FROM users WHERE user_id= " + "'" + user.getUserID() + "' AND " +  "password=" + "'" + user.getPassword() + "';";

        System.out.println(query); //DEBUG

        CachedRowSetImpl cachedRowSetImpl;
        Connection connection = connect(ConnectionType.SINGLEQUERY);
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            cachedRowSetImpl = new CachedRowSetImpl();
            cachedRowSetImpl.populate(resultSet);
            resultSet.close();
            statement.close();
            disconnect(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            disconnect(connection);
            return false;
        }
        try {
            while(cachedRowSetImpl.next()){
                if (cachedRowSetImpl.getString("User_id").equals(user.getUserID())){
                    user.setName(cachedRowSetImpl.getString("Name"));
                    user.setSurname(cachedRowSetImpl.getString("Surname"));
                    user.setEmail(cachedRowSetImpl.getString("Email"));
                    if(cachedRowSetImpl.getString("Type").equals("Admin")) {
                        user.setAdministrationRole();
                    }
                    System.out.println("There is a match!"); //DEBUG
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public boolean createUserRecord(UserBean user) {
        String checkUsernameEsistance = "SELECT User_id FROM Users WHERE User_id='" + user.getUserID() +"';";
        System.out.println(checkUsernameEsistance);

        String query = "INSERT INTO users (user_id, password, name, surname, email, type) VALUES ('" + user.getUserID() + "', '" +
                user.getPassword() + "', '" + user.getName() + "', '" + user.getSurname() + "', '" + user.getEmail() + "', '" + user.getType() + "');";

        System.out.println(query); //DEBUG

        Connection connection = connect(ConnectionType.SINGLEQUERY);
        Statement statement;
        ResultSet resultSet;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(checkUsernameEsistance);

            if(resultSet.next()){
                System.out.println("This username (" + user.getUserID() +") already exits!");
                statement.close();
                resultSet.close();
                disconnect(connection);
                return false;
            }

            resultSet.close();
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            disconnect(connection);
            return false;
        }
        disconnect(connection);
        return true;
    }
}
