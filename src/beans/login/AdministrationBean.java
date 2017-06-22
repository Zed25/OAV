package beans.login;

import Controllers.SerializeInstrumentController;
import Controllers.UsersController;
import DAO.UserDAO;
import enumerations.ErrorType;

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
        this.setType(user.getType());
    }

    /**
     * It call the user's controller to register a new user (only administrator can do this)
     * @param user
     * @return boolan
     */
    public ErrorType newUserRegistration(UserBean user){
        if(user.isFull())
            return UsersController.getUsersControllerInstance().createNewUserRecord(user, this.getType());
        return ErrorType.MISS_VAL;
    }

    /**
     * it gets all the information to serialize controller in order to insert a new instrument into db
     * @return boolean value
     */
    public ErrorType serializeInstrument(InstrumentBean instrumentBean) {
        return SerializeInstrumentController.getSerializeInstrumentControllerInstance().serializeInstrument(this,
                instrumentBean);
    }


}
