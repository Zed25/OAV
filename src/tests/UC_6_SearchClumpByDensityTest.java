import beans.login.ClumpBean;
import beans.login.InstrumentBean;
import beans.login.ResultBean;
import beans.login.UserBean;
import enumerations.ErrorType;
import model.Clump;
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
public class UC_6_SearchClumpByDensityTest {
    private UserBean user;

    @Parameterized.Parameters
    public static Collection<UserBean> data() {

        //user admin
        UserBean userAdmin = new UserBean();
        userAdmin.setUserID("Zed");
        userAdmin.setPassword("root");
        userAdmin.login();

        return Arrays.asList(
                userAdmin
        );
    }

    public UC_6_SearchClumpByDensityTest(UserBean user) {
        this.user = user;
    }

    @Test
    public void searchClumpByDensity() throws Exception {

        List<ClumpBean> clumpBeans = new ArrayList<>();
        ResultBean percPop = new ResultBean();

        Assert.assertEquals("this test must be passed, but it isn't", ErrorType.NO_ERR,
                user.getClumpsByDensity(clumpBeans, percPop));
    }
}
