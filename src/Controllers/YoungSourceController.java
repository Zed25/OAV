package Controllers;
import DAO.YoungSourceDAO;
import beans.login.SourceBean;
import beans.login.ResultBean;
import beans.login.SearchBean;
import com.sun.rowset.CachedRowSetImpl;
import enumerations.ErrorType;
import model.Clump;
import model.Source;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dilettalagom on 02/07/17.
 */
public class YoungSourceController {

    private static YoungSourceController instance = null;
    public static synchronized YoungSourceController getInstance() {
        if (instance == null)
            instance = new YoungSourceController();
        return instance;
    }
    private YoungSourceController() {
    }

    /*Creating the SouceBeans instances used to send the query's result to the jsp,
    * from the selected Clump instace*/
    public ErrorType getYoungSource(SearchBean searchBean, ResultBean resultBean) {

        Clump clump = new Clump();
        clump.setClumpID(searchBean.getClumpID());

        List<Source> resultSources = findYoungSource(clump.getClumpID());
        if (resultSources == null) {
            return ErrorType.NO_RESULTS;
        } else {
            List<SourceBean> resultSourceList = new ArrayList<>();
            for (Source source : resultSources) {
                SourceBean sourceBean = new SourceBean();
                sourceBean.setSourceID(source.getSourceID());
                resultSourceList.add(sourceBean);
            }
            resultBean.setSourceBeans(resultSourceList);

            return ErrorType.NO_ERR;
        }
    }

    //UC11
    /*Creating the Souces instances used to set the SoucesBeans values*/
    private List<Source> findYoungSource(int clumpID){
        YoungSourceDAO dao = new YoungSourceDAO();
        CachedRowSetImpl result = dao.searchYoungSource(clumpID);
        List<Source> youngSources = new ArrayList<>();
        try {
            while (result.next()) {
                Source source = new Source();
                source.setSourceID(result.getString("V1source"));
                youngSources.add(source);

            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        if (youngSources.isEmpty())
            youngSources = null;
        return youngSources;
    }

}
