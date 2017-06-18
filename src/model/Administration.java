package model;

/**
 * Created by simone on 01/06/17.
 */
public class Administration extends User{

    public Administration(User user){
        super(user.getName(), user.getSurname(), user.getUserID(), user.getPassword(),
                user.getEmail(), user.getType());
    }

}
