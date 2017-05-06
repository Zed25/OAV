package Controllers;

import DAO.AgencyDAO;
//import DAO.SearchDAO;
import DAO.ClumpDAO;
import DAO.SearchDAO;
import beans.login.AgencyBean;
import beans.login.ClumpBean;
import beans.login.search.ResultBean;
import beans.login.search.SearchBean;
import com.sun.rowset.CachedRowSetImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrea on 29/03/17.
 */

public class SearchController {
    private static SearchController instance;

    private SearchController() {
    }

    //UC 4
    public void FindObjectInMap(SearchBean bean, ResultBean resBean) {  //band==0 -> 1 banda, else tutte
        SearchDAO dao = new SearchDAO();
        CachedRowSetImpl result = dao.searchObjectInMap(bean);
        resBean.populateSourcesInMap(result, bean);
    }

    //UC 5
    public void searchClumpByID(ClumpBean clumpBean, ResultBean resBean) {
        SearchDAO dao = new SearchDAO();
        CachedRowSetImpl result = dao.searchClumpByID(clumpBean);
        resBean.populateClumpsByID(result);

    }

    public List<AgencyBean> getAllAgencies(){
        AgencyDAO agencyDAO = new AgencyDAO();
        return agencyDAO.getAll();
    }

    public List<ClumpBean> getClumpsByDencity(float minD, float maxD) {
        ClumpDAO clumpDAO = new ClumpDAO();
        CachedRowSetImpl cachedRowSet = clumpDAO.getClumpsByDencity(minD, maxD);

        List<ClumpBean> clumps = new ArrayList<>();
        try {
            while(cachedRowSet.next()){
                ClumpBean clump = new ClumpBean();
                clump.setClumpID(cachedRowSet.getString("clumpid"));
                clump.setDensity(cachedRowSet.getString("surfacedensity"));
                clumps.add(clump);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        if(clumps.size() == 0)
            return null;

        return clumps;
    }


    public static synchronized SearchController getInstance() {
        if (instance == null)
            instance = new SearchController();
        return instance;
    }
}
