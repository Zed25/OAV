package beans.login;

import Controllers.UsersController;
import DAO.UserDAO;

/**
 * Created by simone on 30/03/17.
 */
public class AdministrationBean extends UserBean {

    /**
     * administration bean constructor
     * it cannot be create by jsp page.
     * According to the metamorphosis pattern, it can be initialized only from a UserBean object
     * @param user
     */
    public AdministrationBean(UserBean user){
        this.setUserID(user.getUserID());
        this.setPassword(user.getPassword());
        this.setName(user.getName());
        this.setSurname(user.getSurname());
        this.setEmail(user.getEmail());
    }

    /**
     * It' call the user's controller to register a new user (only administrator can do this)
     * @param user
     * @return boolan
     */
    public boolean newUserRegistration(UserBean user){
        if(user.isFull())
            return UsersController.getUsersControllerInstance().createNewUserRecord(user);
        return false;
    }

}
