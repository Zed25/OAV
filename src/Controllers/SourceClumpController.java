package Controllers;


import beans.login.SourceBean;
import beans.login.search.*;
import com.sun.rowset.CachedRowSetImpl;
import enumerations.ErrorType;
import model.Source;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dilettalagom on 30/06/17.
 */
public class SourceClumpController {

    private static SourceClumpController instance = null;
    public static synchronized SourceClumpController getInstance() {
        if (instance == null)
            instance = new SourceClumpController();
        return instance;
    }
    private SourceClumpController() {
    }

    //Adapter
    public ErrorType getElementsFromBean(SearchBean searchBean, ResultBean resultBean) {

        List<Source> sources = findSourceinClump(searchBean.getClumpID(),Float.parseFloat(searchBean.getBand()));
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

    //UC8
    private List<Source> findSourceinClump (int clumpID, Float band){
        SourceinClumpDAO querydao = new SourceinClumpDAO();
        CachedRowSetImpl resultFound = querydao.searchSource(clumpID, band);
        List<Source> sourcesfound = new ArrayList<>();
        try {
            while (resultFound.next()) {
                Source source = new Source();
                source.setSourceID(resultFound.getString("sourceid"));
                sourcesfound.add(source);

            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        if (sourcesfound.isEmpty())
            sourcesfound = null;
        return sourcesfound;
    }


}
