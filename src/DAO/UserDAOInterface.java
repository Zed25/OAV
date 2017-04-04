package DAO;

import beans.login.UserBean;

/**
 * Created by simone on 29/03/17.
 */
public interface UserDAOInterface {

    public boolean login(UserBean user);

    public boolean createUserRecord(UserBean user);
}
