package beans.login;

/**
 * Created by simone on 26/03/17.
 */
public class UserBean {
    private String name, surname, userID, password, email;

    public UserBean(String name, String surname, String userID, String password, String email) {
        this.name = name;
        this.surname = surname;
        this.userID = userID;
        this.password = password;
        this.email = email;
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
}
