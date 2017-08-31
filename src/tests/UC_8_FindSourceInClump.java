/**
 * Created by dilettalagom on 08/07/17.
 */
import beans.login.ResultBean;
import beans.login.SearchBean;
import enumerations.ErrorType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(value = Parameterized.class)
public class UC_8_FindSourceInClump {

    private SearchBean searchBean;
    private ResultBean resultBean = new ResultBean();

    @Parameterized.Parameters
    public static Collection<SearchBean> data() {

        SearchBean successBean = new SearchBean();
        successBean.setClumpID(179761);
        successBean.setBand("250"); //clump with sources [179761, 250.0]

        SearchBean noResultBean = new SearchBean();
        noResultBean.setClumpID(179761);
        noResultBean.setBand("160"); //clump with no sources [179761,160,0]

        return Arrays.asList(successBean, noResultBean);
    }


    public UC_8_FindSourceInClump(SearchBean searchBean) {
        this.searchBean = searchBean;
    }

    @Test
    public void findSourceInClump() throws Exception {

        switch (searchBean.getBand()) {

            case "250":
                Assert.assertEquals("this test must be passed, but it isn't", ErrorType.NO_ERR,
                        searchBean.sourceInClump(searchBean, resultBean));

                Assert.assertEquals("this test must be passed, but it isn't", "MG042.0005+00.6250",
                        resultBean.getSourceBeans().get(0).getSourceID());

                Assert.assertNotNull("this test must be passed, but it isn't",
                        searchBean.sourceInClump(searchBean, resultBean));

                break;

            case "160":
                Assert.assertEquals("this test must be passed, but it isn't", ErrorType.NO_RESULTS,
                        searchBean.sourceInClump(searchBean, resultBean));

                Assert.assertEquals("this test must be passed, but it isn't", null,
                        resultBean.getSourceBeans());

                Assert.assertNotNull("this test must be passed, but it isn't",
                        searchBean.sourceInClump(searchBean, resultBean));
                break;
        }

    }
}
