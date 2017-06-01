package DAO;

import beans.login.AdministrationBean;
import beans.login.UserBean;
import com.sun.rowset.CachedRowSetImpl;
import enumerations.ConnectionType;
import enumerations.ErrorType;

import java.sql.*;

/**
 * Created by simone on 29/03/17.
 */
public class UserDAO extends SuperDAO{

    public CachedRowSetImpl login(String username, String password) {

        String query = "SELECT * FROM users WHERE user_id=? AND password=?;";

        System.out.println(query); //DEBUG

        CachedRowSetImpl cachedRowSetImpl;
        Connection connection = connect(ConnectionType.SINGLEQUERY);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
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

    public ErrorType createUserRecord(UserBean user) {
        String checkUsernameEsistance = "SELECT User_id FROM Users WHERE User_id=?;";
        System.out.println(checkUsernameEsistance);

        String query = "INSERT INTO users (user_id, password, name, surname, email, type) VALUES (?,?,?,?,?,?);";

        System.out.println(query); //DEBUG

        Connection connection = connect(ConnectionType.SINGLEQUERY);
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        try {
            preparedStatement = connection.prepareStatement(checkUsernameEsistance);
            preparedStatement.setString(1, user.getUserID());
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                System.out.println("This username (" + user.getUserID() +") already exits!");
                preparedStatement.close();
                resultSet.close();
                disconnect(connection);
                return ErrorType.USER_ALREADY_EXISTS;
            }

            resultSet.close();
            preparedStatement.close();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getUserID());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getSurname());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getType());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            disconnect(connection);
            return ErrorType.GEN_ERR;
        }
        disconnect(connection);
        return ErrorType.NO_ERR;
    }
}
