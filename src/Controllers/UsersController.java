package Controllers;

import DAO.UserDAO;
import beans.login.AdministrationBean;
import beans.login.UserBean;
import com.sun.rowset.CachedRowSetImpl;
import enumerations.ErrorType;
import model.Administration;
import model.User;

import javax.jws.soap.SOAPBinding;
import java.sql.SQLException;

/**
 * Created by simone on 29/03/17.
 */
public class UsersController {

    private static UsersController usersControllerInstance = null;

    private UsersController() {
    }

    public ErrorType login(UserBean userBean){
        User user = checkUserEsistence(userBean.getUserID(), userBean.getPassword());

        if(user == null)
            return ErrorType.GEN_ERR;

        if(user.isLogged()) { //a user is logged only if all his required attributes aren't empty
            fillUserBeanAfterLogin(userBean, user);
            return ErrorType.NO_ERR;
        }

        return ErrorType.GEN_ERR;

    }

    private void fillUserBeanAfterLogin(UserBean userBean, User user) {
        userBean.setName(user.getName());
        userBean.setSurname(user.getSurname());
        userBean.setEmail(user.getEmail());
        userBean.setType(user.getType());
        if(user.isAdmin())
            userBean.setAdministrationRole();
        userBean.setLogged(true);
    }

    public User checkUserEsistence(String username, String password){
        UserDAO userDAO = new UserDAO();
        CachedRowSetImpl cachedRowSet = userDAO.login(username, password);
        if(cachedRowSet == null)
            return null;

        User user;
        try {
            cachedRowSet.next();
            user = new User(cachedRowSet.getString("name"),
                    cachedRowSet.getString("surname"),
                    cachedRowSet.getString("user_id"),
                    cachedRowSet.getString("password"),
                    cachedRowSet.getString("email"),
                    cachedRowSet.getString("type"));
            user.setLogged();
            if(user.isLogged())
                if(user.getType().equals("Admin"))
                    user.setAdministrationRole(new Administration(user));
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean createNewUserRecord(UserBean user) {
        UserDAO userDAO = new UserDAO();
        return userDAO.createUserRecord(user);
    }

    public static synchronized UsersController getUsersControllerInstance() {
        if(usersControllerInstance == null)
            usersControllerInstance = new UsersController();
        return usersControllerInstance;
    }
}
