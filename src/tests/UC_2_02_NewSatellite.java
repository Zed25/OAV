import Controllers.SerializeSatelliteController;
import beans.login.SatelliteBean;
import beans.login.UserBean;
import enumerations.ErrorType;
import model.Agency;
import model.Satellite;
import model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by simone on 18/06/17.
 */
@RunWith(value = Parameterized.class)
public class UC_2_02_NewSatellite {
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
        normalUser.login();

        return Arrays.asList(
                userAdmin,
                normalUser
        );
    }

    public UC_2_02_NewSatellite(UserBean user) {
        this.user = user;
    }

    @Test
    public void insertSatellite() throws Exception {

        SatelliteBean satellite = new SatelliteBean();
        satellite.setName("XSatellite");
        satellite.setStartMissionDate("18 June, 2017");
        satellite.setEndMissionDate("23 June, 2017");
        satellite.setAgenciesLinked("ESA,NASA,PROVA");

        if(this.user.isAdmin()) {
            Assert.assertEquals("this test must be passed, but it isn't now", ErrorType.NO_ERR,
                    satellite.serializeSatellite(this.user));
        }else{
            Assert.assertEquals("this user isn't an admin, so it couldn't perform this action", ErrorType.NO_ADMIN,
                    satellite.serializeSatellite(this.user));
        }
    }
}
