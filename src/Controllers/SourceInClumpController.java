package Controllers;


import DAO.SourceInClumpDAO;
import beans.login.SourceBean;
import beans.login.*;
import com.sun.rowset.CachedRowSetImpl;
import enumerations.ErrorType;
import model.Clump;
import model.Source;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dilettalagom on 30/06/17.
 */
public class SourceInClumpController {

    private static SourceInClumpController instance = null;
    public static synchronized SourceInClumpController getInstance() {
        if (instance == null)
            instance = new SourceInClumpController();
        return instance;
    }
    private SourceInClumpController() {
    }

    /*Creating the SouceBeans instances used to send the query's result to the jsp,
    * from the selected Clump instance*/
    public ErrorType getElementsFromBean(SearchBean searchBean, ResultBean resultBean) {

        Clump clump = new Clump();
        clump.setClumpID(searchBean.getClumpID());
        clump.setBand(Float.parseFloat(searchBean.getBand()));

        List<Source> sources = findSourceinClump(clump.getClumpID(), clump.getBand() );
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
    /*Creating the Souces instances used to set the SoucesBeans values*/
    private List<Source> findSourceinClump(int clumpID, Float band){
        SourceInClumpDAO querydao = new SourceInClumpDAO();
        CachedRowSetImpl resultFound = querydao.searchSource(clumpID, band);
        List<Source> sourcesfound = new ArrayList<>();
        try {
            while (resultFound.next()) {
                Source source = new Source();
                source.setSourceID(resultFound.getString("source"));
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
