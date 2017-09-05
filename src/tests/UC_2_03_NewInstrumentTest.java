import DAO.InstrumentDAO;
import beans.login.InstrumentBean;
import beans.login.SatelliteBean;
import beans.login.UserBean;
import enumerations.ErrorType;
import model.Band;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by simone on 08/07/17.
 */
@RunWith(value = Parameterized.class)
public class UC_2_03_NewInstrumentTest {
    private UserBean user;

    @Parameterized.Parameters
    public static Collection<UserBean> data() {

        //user admin
        UserBean userAdmin = new UserBean();
        userAdmin.setUserID("Zeddicus");
        userAdmin.setPassword("Zeddicus");
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

    public UC_2_03_NewInstrumentTest(UserBean user) {
        this.user = user;
    }

    @Test
    public void insertInstrument() throws Exception {

        InstrumentBean instrumentBean = new InstrumentBean();
        instrumentBean.setName("XINS");
        instrumentBean.setSatellite("Herschel");
        instrumentBean.setMap("HiGal");
        instrumentBean.setBandsFromFormString("160.0, 350.0,");



        if(this.user.isAdmin()) {
            Assert.assertEquals("this test must be passed, but it isn't", ErrorType.NO_ERR,
                    user.getAdministrationRole().serializeInstrument(instrumentBean));

            InstrumentDAO instrumentDAO = new InstrumentDAO();
            instrumentDAO.deleteInstrumentAndConnections(instrumentBean.getName());
        }
    }
}
