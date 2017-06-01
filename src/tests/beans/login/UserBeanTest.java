package beans.login;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import javax.jws.soap.SOAPBinding;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Created by simone on 30/05/17.
 */
@RunWith(value = Parameterized.class)
public class UserBeanTest {

    private UserBean user;

    @Parameterized.Parameters
    public static Collection<UserBean> data() {
        UserBean user1 = new UserBean();
        user1.setUserID("Zed");
        user1.setPassword("root");
        user1.login();

        UserBean user2 = new UserBean();
        user2.setUserID("Zed");
        user2.setPassword("root");
        user2.login();
        user2.setPassword("");

        UserBean user3 = new UserBean();
        user3.setUserID("Zed");
        user3.setPassword("root");
        user3.login();
        user3.setName("");
        user3.setEmail("");

        return Arrays.asList(
                user1,
                user2
        ); //return an admin user logged
    }

    public UserBeanTest(UserBean userBean){
        this.user = userBean;
    }

    @Test
    public void login() throws Exception {
        UserBean user = new UserBean();

        Assert.assertEquals("Name isn't empty", "", user.getName());
        Assert.assertEquals("Surname isn't empty", "", user.getSurname());
        Assert.assertEquals("Password isn't empty", "", user.getPassword());
        Assert.assertEquals("UserID isn't empty", "", user.getUserID());
        Assert.assertEquals("Email isn't empty", "", user.getEmail());
        Assert.assertEquals("Type isn't empty", "", user.getType());
        Assert.assertNull("AdministrationRole isn't null", user.getAdministrationRole());
        Assert.assertFalse("the user results logged", user.isLogged());

        user.setUserID("Zed");
        user.setPassword("root");
        user.login();

        Assert.assertEquals("Name is wrong", "Simone", user.getName());
        Assert.assertEquals("Surname is wrong", "Mancini", user.getSurname());
        Assert.assertEquals("Password is wrong", "root", user.getPassword());
        Assert.assertEquals("UserID is wrong", "Zed", user.getUserID());
        Assert.assertEquals("Email is wrong", "2simonemancini5@gmail.com", user.getEmail());
        Assert.assertEquals("Type is wrong", "Admin", user.getType());
        Assert.assertNotNull("This bean should have administration role", user.getAdministrationRole());
        Assert.assertTrue("the user results logged", user.isLogged());
    }

    @Test
    public void isFull() throws Exception {

        //if one attribute is empty isFull should return false
        if(this.user.getName().equals("") ||
                this.user.getSurname().equals("") ||
                this.user.getUserID().equals("") ||
                this.user.getPassword().equals("") ||
                this.user.getEmail().equals("") ||
                this.user.getType().equals(""))
            Assert.assertFalse("the user results full", this.user.isFull());
        else //else it should return true
            Assert.assertTrue("the user doesn't result full", this.user.isFull());

    }

    @Test
    public void emptyBean() throws Exception {

        //check if emptyBean clean the bean completely
        this.user.emptyBean();

        Assert.assertEquals("Name isn't empty", "", user.getName());
        Assert.assertEquals("Surname isn't empty", "", user.getSurname());
        Assert.assertEquals("Password isn't empty", "", user.getPassword());
        Assert.assertEquals("UserID isn't empty", "", user.getUserID());
        Assert.assertEquals("Email isn't empty", "", user.getEmail());
        Assert.assertEquals("Type isn't empty", "", user.getType());
        Assert.assertNull("AdministrationRole isn't null", user.getAdministrationRole());
        Assert.assertFalse("the user results logged", user.isLogged());
    }
}