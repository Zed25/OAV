import beans.login.UserBean;
import enumerations.ErrorType;
import model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import javax.jws.soap.SOAPBinding;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by simone on 02/06/17.
 */
@RunWith(value = Parameterized.class)
public class UC_2_01_NewUserRegistrationTest {

    private UserBean user;

    @Parameterized.Parameters
    public static Collection<UserBean> data() {

        //user admin
        UserBean userAdmin = new UserBean();
        userAdmin.setUserID("Zed");
        userAdmin.setPassword("root");
        userAdmin.login();

        //user no admin
        UserBean normalUser = new UserBean();
        normalUser.setUserID("mario.rossi");
        normalUser.setPassword("mario.rossi");

        return Arrays.asList(
                userAdmin,
                normalUser
        );
    }

    public UC_2_01_NewUserRegistrationTest(UserBean user) {
        this.user = user;
    }

    @Test
    public void newUserRegistration() throws Exception {
        if(this.user.getType().equals("Admin")){
            //user with a userID already chosen
            UserBean newUser = new UserBean();
            newUser.setName("Luigi");
            newUser.setSurname("Verdi");
            newUser.setUserID("mario.rossi");
            newUser.setPassword("mario.rossi");
            newUser.setEmail("luigi.verdi@gmail.com");
            newUser.setType("User");

            Assert.assertEquals("User with a userID already chosen added to db",
                    ErrorType.ALREADY_EXISTS, this.user.newUserRegistration(newUser));

            //user without some info
            newUser.setName("");
            newUser.setEmail("");

            Assert.assertEquals("User insert in db without some info",
                    ErrorType.MISS_VAL, this.user.newUserRegistration(newUser));

            //user with right info
            newUser.setName("Luigi");
            newUser.setUserID("luigi.verdi");
            newUser.setPassword("luigi.verdi");
            newUser.setEmail("luigi.verdi@gmail.com");

            Assert.assertEquals("not realized insertion, it returned a error",
                    ErrorType.NO_ERR, this.user.newUserRegistration(newUser));

        }else {

            UserBean newUser = new UserBean();
            newUser.setName("Luigi");
            newUser.setSurname("Verdi");
            newUser.setUserID("mario.rossi");
            newUser.setPassword("mario.rossi");
            newUser.setEmail("luigi.verdi@gmail.com");
            newUser.setType("User");

            //this kind of user couldn't create a new user
            Assert.assertEquals("user created by a normal user",
                    ErrorType.NO_ADMIN, this.user.newUserRegistration(newUser));
        }
    }
}
