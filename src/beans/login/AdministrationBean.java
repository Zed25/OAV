package beans.login;

import Controllers.UsersController;
import DAO.UserDAO;

/**
 * Created by simone on 30/03/17.
 */
public class AdministrationBean extends UserBean {

    public AdministrationBean(UserBean user){
        this.setUserID(user.getUserID());
        this.setPassword(user.getPassword());
        this.setName(user.getName());
        this.setSurname(user.getSurname());
        this.setEmail(user.getEmail());
    }

    public boolean newUserRegistration(UserBean user){
        if(user.isFull())
            return UsersController.getUsersControllerInstance().createNewUserRecord(user);
        return false;
    }

}
