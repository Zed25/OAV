package Controllers;

import DAO.SearchObjectDAO;
import beans.login.ClumpBean;
import beans.login.SourceBean;
import beans.login.search.ResultBean;
import beans.login.search.SearchBean;
import com.sun.rowset.CachedRowSetImpl;
import enumerations.ErrorType;
import model.Clump;
import model.Source;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrea on 01/06/17.
 */
public class SearchObjectInMapController {
    private static SearchObjectInMapController instance;

    private SearchObjectInMapController() {
    }

    //Adapter
    public ErrorType FindObjectInMapFromBean(SearchBean bean, ResultBean resBean) {  //band==0 -> 1 banda, else tutte
        //CLUMPS
        if (bean.getMapName().equals("HiGal")) {
            List<Clump> clumps = FindClumpInMap(bean.getClumpID(), bean.getRealBand());
            if (clumps == null)
                return ErrorType.BABOON;
            else {
                List<ClumpBean> clumpBeans = new ArrayList<>();
                for (Clump clump : clumps) {
                    ClumpBean clumpBean = new ClumpBean();
                    clumpBean.setClumpID(clump.getClumpID());
                    clumpBean.setBand(clump.getBand());
                    clumpBean.setFluxValue(clump.getFluxValue());
                    clumpBeans.add(clumpBean);
                }
                resBean.setClumpBeans(clumpBeans);
                return ErrorType.NO_ERR;
            }
        //SOURCES
        } else {
            List<Source> sources = FindSourceInMap(bean.getMapName(), bean.getRealBand());
            if (sources == null)
                return ErrorType.BABOON;
            else {
                List<SourceBean> sourceBeans = new ArrayList<>();
                for (Source source : sources) {
                    SourceBean sourceBean = new SourceBean();
                    sourceBean.setSourceID(source.getSourceID());
                    sourceBean.setBand(source.getBand());
                    sourceBean.setFluxValue(source.getFluxValue());
                    sourceBeans.add(sourceBean);
                }
                resBean.setSourceBeans(sourceBeans);
                return ErrorType.NO_ERR;
            }
        }
    }

    //KUC 4 -> SOURCE
    public List<Source> FindSourceInMap(String mapName, float band){
        SearchObjectDAO dao = new SearchObjectDAO();
        CachedRowSetImpl result = dao.searchObjectInMap(mapName, band, 0);
        List<Source> sources = new ArrayList<>();
        try {
            while (result.next()) {
                Source source = new Source();
                source.setSourceID(result.getString("sourceid"));
                source.setFluxValue(result.getFloat("value"));
                source.setBand(result.getFloat("band"));
                sources.add(source);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return sources;
    }

    //KUC 4 -> CLUMP
    public List<Clump> FindClumpInMap(int clumpID, float band) {
        SearchObjectDAO dao = new SearchObjectDAO();
        CachedRowSetImpl result = dao.searchObjectInMap("HiGal", band, clumpID);
        List<Clump> clumps = new ArrayList<>();
        try {
            while (result.next()) {
                Clump clump = new Clump();
                clump.setClumpID(result.getInt("clumpid"));
                clump.setFluxValue(result.getFloat("value"));
                clump.setBand(result.getFloat("band"));
                clumps.add(clump);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return clumps;
    }

    public static synchronized SearchObjectInMapController getInstance() {
        if (instance == null)
            instance = new SearchObjectInMapController();
        return instance;
    }
}
