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
        UserDAO userDAO= new UserDAO();
        return userDAO.login(userData);
    }

    public static synchronized UsersController getUsersControllerInstance() {
        if(usersControllerInstance == null)
            usersControllerInstance = new UsersController();
        return usersControllerInstance;
    }
}
