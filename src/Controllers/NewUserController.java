package Controllers;

import DAO.UserDAO;
import beans.login.UserBean;
import com.sun.rowset.CachedRowSetImpl;
import enumerations.ErrorType;
import model.User;

import java.sql.SQLException;

/**
 * Created by simone on 29/03/17.
 */
public class NewUserController {

    private static NewUserController newUserControllerInstance = null;

    private NewUserController() {
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
        if(!userIDPasswordMinSixChar(user.getUserID(), user.getPassword()))
            return ErrorType.SHORT_VALUES;
        if(!acceptedHostMail(user.getEmail()))
            return ErrorType.WRONG_HOST_MAIL;
        if(userType.equals("Admin")) {
            if (user.hasAllInfoToInsert()) {
                UserDAO userDAO = new UserDAO();
                return userDAO.insert(user);
            }
            return ErrorType.MISS_VAL;
        }
        return ErrorType.NO_ADMIN;
    }

    private boolean acceptedHostMail(String email) {
        for(int i = 0; i < email.length(); i++){
            if(email.substring(i,i+1).equals("@")){
                String hostMail = email.substring(i + 1, email.length());
                if(hostMail.equals("gmail.com") || hostMail.equals("libero.it") || hostMail.equals("alice.it")) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    /**check if values username and password have minimum six char**/
    private boolean userIDPasswordMinSixChar(String userID, String password) {
        if(userID.length() > 6 && password.length() > 6)
            return true;
        return false;
    }

    public static synchronized NewUserController getNewUserControllerInstance() {
        if(newUserControllerInstance == null)
            newUserControllerInstance = new NewUserController();
        return newUserControllerInstance;
    }
}
