/**
 * Created by dilettalagom on 08/07/17.
 */
import Controllers.YoungSourceController;
import beans.login.ResultBean;
import beans.login.SearchBean;
import enumerations.ErrorType;
import model.Clump;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(value = Parameterized.class)
public class UC_11_FindYoungSourcesTest {

    private SearchBean searchBean;
    private ResultBean resultBean = new ResultBean();

    @Parameterized.Parameters
    public static Collection<SearchBean> data(){

        SearchBean testSuccessBean= new SearchBean();
        testSuccessBean.setClumpID(179761); //clump with sources

        SearchBean testErrorBean = new SearchBean();
        testErrorBean.setClumpID(178173); //clump with no sources

        return Arrays.asList(testSuccessBean, testErrorBean);
    }

    public UC_11_FindYoungSourcesTest(SearchBean searchBean) {
        this.searchBean = searchBean;
    }

    @Test
    public void findYoungSource() throws Exception {

        Assert.assertNotNull("the selected clumps is null",
                searchBean.findYoungSuorce(searchBean, resultBean));

        switch (searchBean.getClumpID()) {

            case 179761:

                Assert.assertEquals("this test must be passed, but it isn't", ErrorType.NO_ERR,
                        searchBean.findYoungSuorce(searchBean, resultBean));

                Assert.assertEquals("this test must be passed, but it isn't", "G042.2646+00.5475",
                        resultBean.getSourceBeans().get(0).getSourceID());
                break;

            case 178173:
                Assert.assertEquals("this test must be passed, but it isn't", ErrorType.NO_RESULTS,
                        searchBean.findYoungSuorce(searchBean, resultBean));

                Assert.assertEquals("this test must be passed, but it isn't", null,
                        resultBean.getSourceBeans());
                break;

        }
    }
}
