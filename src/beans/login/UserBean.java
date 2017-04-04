package beans.login;

import Controllers.UsersController;

/**
 * Created by simone on 26/03/17.
 */
public class UserBean {
    private String name, surname, userID, password, email, type;
    private boolean logged;
    private AdministrationBean administrationRole = null;

    public UserBean() {
        name = "";
        surname = "";
        userID = "";
        password = "";
        email = "";
        type = "";
        logged = false;
    }

    public boolean login(){
        if(this.getUserID() != null && this.getPassword() != null)
            return UsersController.getUsersControllerInstance().checkUserEsistence(this);
        return false;
    }

    public boolean isFull(){
        if(this.getUserID().equals("") || this.getPassword().equals("") || this.getName().equals("") || this.getSurname().equals("") || this.getEmail().equals(""))
            return false;
        return true;
    }

    public void emptyBean(){
        this.setName("");
        this.setSurname("");
        this.setUserID("");
        this.setPassword("");
        this.setEmail("");
        this.setType("");
        if(isAdmin())
            this.administrationRole = null;
    }

    public boolean newUserRegistration(UserBean newUser){
        if(this.isAdmin()){
            return this.getAdministrationRole().newUserRegistration(newUser);
        }
        return false;
    }

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
