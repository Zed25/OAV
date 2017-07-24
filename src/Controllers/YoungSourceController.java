package Controllers;
import DAO.YoungSourceDAO;
import beans.login.SourceBean;
import beans.login.ResultBean;
import beans.login.SearchBean;
import com.sun.rowset.CachedRowSetImpl;
import enumerations.ErrorType;
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

    //Adapter
    public ErrorType getYoungSource(SearchBean searchBean, ResultBean resultBean) {

        List<Source> resultSources = findYoungSource(searchBean.getClumpID());
        if (resultSources == null) {
            return ErrorType.NO_RESULTS;
        } else {
            List<SourceBean> finalSourceList = new ArrayList<>();
            for (Source source : resultSources) {
                SourceBean sourceBean = new SourceBean();
                sourceBean.setSourceID(source.getSourceID());
                finalSourceList.add(sourceBean);

                System.out.println(sourceBean.getSourceID());
            }
            resultBean.setSourceBeans(finalSourceList);

            return ErrorType.NO_ERR;
        }
    }

    //UC11
    public List<Source> findYoungSource (int clumpID){
        YoungSourceDAO dao = new YoungSourceDAO();
        CachedRowSetImpl result = dao.searchYoungSource(clumpID);
        List<Source> youngSources = new ArrayList<>();
        try {
            while (result.next()) {
                Source source = new Source();
                source.setSourceID(result.getString("V1source"));
                youngSources.add(source);

                System.out.println(source.getSourceID());
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
