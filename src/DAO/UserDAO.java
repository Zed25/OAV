package DAO;

import beans.login.UserBean;
import com.sun.rowset.CachedRowSetImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by simone on 29/03/17.
 */
public class UserDAO implements UserDAOInterface{

    @Override
    public boolean login(UserBean user) {

        String query = "SELECT \"User-id\" FROM \"Utenti\" WHERE \"User-id\" = " + "'" + user.getUserID() + "' AND " +  "\"Password\"=" + "'" + user.getPassword() + "';";

        System.out.println(query); //DEBUG

        CachedRowSetImpl cachedRowSetImpl = DataBaseManager.getInstance().dbQuery(query);

        if(cachedRowSetImpl == null)
            return false;


        try {
            while(cachedRowSetImpl.next()){
                System.out.println(cachedRowSetImpl.get);
                if (cachedRowSetImpl.getString(1).equals(user.getUserID())){
                    System.out.println("There is a match!");
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
