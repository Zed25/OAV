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
public class  UsersController {

    private static UsersController usersControllerInstance = null;

    private UsersController() {
    }

    public ErrorType login(UserBean userBean){
        User user = checkUserEsistence(userBean.getUserID(), userBean.getPassword());

        if(user == null)
            return ErrorType.GEN_ERR;

        if(user.isLogged()) {
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
            if(cachedRowSet.next()) {
                user = new User(cachedRowSet.getString("name"),
                        cachedRowSet.getString("surname"),
                        cachedRowSet.getString("user_id"),
                        cachedRowSet.getString("password"),
                        cachedRowSet.getString("email"),
                        cachedRowSet.getString("type"));
                user.setLogged();
                if (user.isLogged()) //a user is logged only if all his required attributes aren't empty
                    if (user.getType().equals("Admin"))
                        user.setAdministrationRole();
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    /**simple logout**/
    public ErrorType logout(UserBean userBean) {
        userBean.emptyBean();
        return ErrorType.NO_ERR;
    }

    /**View link function
     * ask controller to create new user record in db
     * @param userBean user info to db
     * @param userType who try operation level
     * @return error type. NO_ERR if the operation ends without problems**/
    public ErrorType createNewUserRecord(UserBean userBean, String userType) {
        User user = new User(userBean.getName(), userBean.getSurname(), userBean.getUserID(), userBean.getPassword(),
                userBean.getEmail(), userBean.getType());
        return insertNewUser(user, userType);
    }

    /**create new user record in db
     * @param user user info to db
     * @param userType who try operation level
     * @return error type. NO_ERR if the operation ends without problems**/
    public ErrorType insertNewUser(User user, String userType){
        if(userType.equals("Admin")) {
            if (user.hasAllInfoToInsert()) {
                UserDAO userDAO = new UserDAO();
                return userDAO.insert(user);
            }
            return ErrorType.MISS_VAL;
        }
        return ErrorType.NO_ADMIN;
    }

    public static synchronized UsersController getUsersControllerInstance() {
        if(usersControllerInstance == null)
            usersControllerInstance = new UsersController();
        return usersControllerInstance;
    }
}
