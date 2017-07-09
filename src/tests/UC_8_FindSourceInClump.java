/**
 * Created by dilettalagom on 08/07/17.
 */
import Controllers.FileController;
import Controllers.SearchController;
import Controllers.SourceClumpController;
import Controllers.YoungSourceController;
import beans.login.UserBean;
import beans.login.search.ResultBean;
import beans.login.search.SearchBean;
import enumerations.ErrorType;
import model.Band;
import model.Clump;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@RunWith(value = Parameterized.class)
public class UC_8_FindSourceInClump {

    private SearchBean searchBean;
    private ResultBean resultBean;

    @Parameterized.Parameters
    public static Collection<SearchBean> data() {

        SearchBean successBean = new SearchBean();
        successBean.setClumpID(179761);
        successBean.setBand("250"); //clump with sources [179761, 250.0]

        SearchBean errorBean = new SearchBean();
        errorBean.setClumpID(179761);
        errorBean.setBand("160"); //clump with no sources [179761,160,0]

        return Arrays.asList(successBean, errorBean);
    }


    public UC_8_FindSourceInClump(SearchBean searchBean){
        this.searchBean = searchBean;
    }

    @Test
    public void findSourceInClump() throws Exception{
        Assert.assertEquals("this test must be passed, but it isn't", ErrorType.NO_ERR,
                searchBean.sourceInClump(searchBean, resultBean));
    }
    @Test
    public void findSourceInClump1() throws Exception{
        Assert.assertNotNull("this test must be passed, but it isn't",
                searchBean.sourceInClump(searchBean, resultBean));
    }
}
