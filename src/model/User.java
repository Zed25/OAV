package model;

import beans.login.AdministrationBean;

/**
 * Created by simone on 31/05/17.
 */
public class User {
    private String name, surname, userID, password, email, type;
    private boolean logged;
    private Administration administrationRole = null;

    //constructor to create a user object with login parameters
    public User(String userID, String password) {
        this.userID = userID;
        this.password = password;
    }

    //full user object constructor
    public User(String name, String surname, String userID, String password, String email, String type) {
        this.name = name;
        this.surname = surname;
        this.userID = userID;
        this.password = password;
        this.email = email;
        this.type = type;
    }

    /**check if the user object has all the info required to insert in db**/
    public boolean hasAllInfoToInsert() {
        if(this.getName().equals("") ||
                this.getSurname().equals("") ||
                this.getUserID().equals("") ||
                this.getPassword().equals("") ||
                this.getEmail().equals("") ||
                this.getType().equals(""))
            return false;

        return true;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged() {
        if(!this.getName().equals("") ||
                !this.getSurname().equals("") ||
                !this.getUserID().equals("") ||
                !this.getPassword().equals("") ||
                !this.getEmail().equals("") ||
                !this.getType().equals(""))
            this.logged = true;
        else
            this.logged = false;
    }

    public Administration getAdministrationRole() {
        return administrationRole;
    }

    public void setAdministrationRole(Administration administrationRole) {
        this.administrationRole = administrationRole;
    }

    public boolean isAdmin(){
        if(this.administrationRole != null)
            return true;
        return false;
    }
}
