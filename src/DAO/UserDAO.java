package DAO;

import beans.login.AdministrationBean;
import beans.login.UserBean;
import com.sun.rowset.CachedRowSetImpl;
import enumerations.ConnectionType;

import java.sql.*;

/**
 * Created by simone on 29/03/17.
 */
public class UserDAO extends SuperDAO{

    public CachedRowSetImpl login(String username, String password) {

        String query = "SELECT * FROM users WHERE user_id= " + "? AND password=?;";

        System.out.println(query); //DEBUG

        CachedRowSetImpl cachedRowSetImpl;
        Connection connection = connect(ConnectionType.SINGLEQUERY);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery(query);
            cachedRowSetImpl = new CachedRowSetImpl();
            cachedRowSetImpl.populate(resultSet);
            resultSet.close();
            preparedStatement.close();
            disconnect(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            disconnect(connection);
            return null;
        }
        return cachedRowSetImpl;
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
