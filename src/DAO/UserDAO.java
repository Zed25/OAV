package DAO;

import beans.login.AdministrationBean;
import beans.login.UserBean;
import com.sun.rowset.CachedRowSetImpl;

import java.sql.SQLException;

/**
 * Created by simone on 29/03/17.
 */
public class UserDAO implements UserDAOInterface{

    @Override
    public boolean login(UserBean user) {

        String query = "SELECT * FROM Users WHERE User_id = " + "'" + user.getUserID() + "' AND " +  "Password=" + "'" + user.getPassword() + "';";

        System.out.println(query); //DEBUG

        CachedRowSetImpl cachedRowSetImpl = DataBaseManager.getInstance().dbQuery(query);

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

    @Override
    public boolean createUserRecord(UserBean user) {
        String checkUsernameEsistance = "SELECT User_id FROM Users WHERE User_id = '" + user.getUserID() +"'";
        System.out.println(checkUsernameEsistance);

        CachedRowSetImpl cachedRowSetImpl = DataBaseManager.getInstance().dbQuery(checkUsernameEsistance);

        try {
            while(cachedRowSetImpl.next()){
                if(cachedRowSetImpl.getString("User_id").equals(user.getUserID()))
                    return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        String query = "INSERT INTO Users (User_id, Password, Name, Surname, Email, Type) VALUES ('" + user.getUserID() + "', '" +
                user.getPassword() + "', '" + user.getName() + "', '" + user.getSurname() + "', '" + user.getEmail() + "', '" + user.getType() + "');";

        System.out.println(query); //DEBUG

        return DataBaseManager.getInstance().insertTuple(query);
    }
}
