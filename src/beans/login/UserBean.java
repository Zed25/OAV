package beans.login;

import Controllers.LoginController;
import Controllers.SearchClumpByDensityController;
import Controllers.NewUserController;
import enumerations.ErrorType;

import java.util.List;

/**
 * Created by simone on 26/03/17.
 */
public class UserBean {
    private String name, surname, userID, password, email, type;
    private boolean logged;
    private AdministrationBean administrationRole = null;

    /**
     * user bean's constructor
     */
    public UserBean() {
        name = "";
        surname = "";
        userID = "";
        password = "";
        email = "";
        type = "";
        logged = false;
    }

    /**
     * login routine. Check if the username and password passed are in the db and if it's true this bean is filled by the constructor
     * @return boolean
     */
    public ErrorType login(){
        if(this.getUserID() != null && this.getPassword() != null) {
            return LoginController.getLoginControllerInstance().login(this);
        }
        return ErrorType.MISS_VAL;
    }

    public ErrorType logout(){
        return LoginController.getLoginControllerInstance().logout(this);
    }

    /**
     * check if the bean's object is full
     * @return boolean
     */
    public boolean isFull(){
        if(this.getUserID().equals("") || this.getPassword().equals("") || this.getName().equals("") || this.getSurname().equals("") || this.getEmail().equals(""))
            return false;
        return true;
    }

    /**
     * clean the bean's object
     */
    public void emptyBean(){
        this.setName("");
        this.setSurname("");
        this.setUserID("");
        this.setPassword("");
        this.setEmail("");
        this.setType("");
        this.setLogged(false);
        if(isAdmin())
            this.administrationRole = null;
    }

    /**
     * if the bean's object colling this method had administration role the newUser data are inserted in the db by the constructor
     * @param newUser
     * @return boolean
     */
    public ErrorType newUserRegistration(UserBean newUser){
        if(this.isAdmin()){
            return this.getAdministrationRole().newUserRegistration(newUser);
        }
        return ErrorType.NO_ADMIN;
    }

    public ErrorType getClumpsByDensity(List<ClumpBean> results){
        ErrorType errorType = SearchClumpByDensityController.getSearchClumpByDensityController().searchPossibleClumpHosts(results);
        return errorType;
    }

    /**GETTER AND SETTER**/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public AdministrationBean getAdministrationRole() {
        return administrationRole;
    }

    public boolean isAdmin(){
        if(administrationRole == null)
            return false;
        return true;
    }

    public void setAdministrationRole() {
        if(administrationRole == null) {
            this.administrationRole = new AdministrationBean(this);
        }
    }
}
