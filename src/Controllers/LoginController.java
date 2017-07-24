package Controllers;

import DAO.UserDAO;
import beans.login.UserBean;
import com.sun.rowset.CachedRowSetImpl;
import enumerations.ErrorType;
import model.User;

import java.sql.SQLException;

/**
 * Created by simone on 24/07/17.
 */
public class LoginController {

    private static LoginController loginControllerInstance = null;

    private LoginController() {
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

    public static synchronized LoginController getLoginControllerInstance() {
        if(loginControllerInstance == null)
            loginControllerInstance = new LoginController();
        return loginControllerInstance;
    }
}
