package Controllers;
import DAO.YoungSourceDAO;
import beans.login.SourceBean;
import beans.login.search.ResultBean;
import beans.login.search.SearchBean;
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

        List<Source> sources = findYoungSource(searchBean.getClumpID());
        if (sources == null) {
            return ErrorType.NO_RESULTS;
        } else {
            List<SourceBean> finalSourceList = new ArrayList<>();
            for (Source source : sources) {
                SourceBean sourceBean = new SourceBean();
                sourceBean.setSourceID(source.getSourceID());
                finalSourceList.add(sourceBean);
            }
            resultBean.setSourceBeans(finalSourceList);

            return ErrorType.NO_ERR;
        }
    }

    //UC11
    private List<Source> findYoungSource (int clumpID){
        YoungSourceDAO dao = new YoungSourceDAO();
        CachedRowSetImpl result = dao.searchYoungSource(clumpID);
        List<Source> youngSources = new ArrayList<>();
        try {
            while (result.next()) {
                Source sorgente = new Source();
                sorgente.setSourceID(result.getString("sourceid")); //?????
                youngSources.add(sorgente);
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
