package Controllers;

import DAO.UserDAO;
import beans.login.UserBean;

/**
 * Created by simone on 29/03/17.
 */
public class UsersController {

    private static UsersController usersControllerInstance = null;

    public UsersController() {
    }

    public boolean checkUserEsistence(UserBean userData){
        UserDAO userDAO = new UserDAO();
        if(userDAO.login(userData)){
            userData.setLogged(true);
            return true;
        }
        return  false;
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
