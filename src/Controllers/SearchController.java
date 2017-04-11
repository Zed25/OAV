package Controllers;

import DAO.AgencyDAO;
//import DAO.SearchDAO;
import beans.login.AgencyBean;
import beans.login.search.ResultBean;
import beans.login.search.SearchBean;
import com.sun.rowset.CachedRowSetImpl;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by andrea on 29/03/17.
 */

public class SearchController {
    private static SearchController instance;

    private SearchController() {
    }

    // non dovra essere void, ma dovra' ritornare una lista di oggetti o un bean che li contiene

    /*public void FindObjectInMap(SearchBean bean, ResultBean resBean) {  //band==0 -> 1 banda, else tutte
        SearchDAO dao = new SearchDAO();
        CachedRowSetImpl result = dao.searchObjectInMap(bean);
        resBean.populate(result);
    }*/

    public List<AgencyBean> getAllAgencies(){
        AgencyDAO agencyDAO = new AgencyDAO();
        return agencyDAO.getAll();
    }


    public static synchronized SearchController getInstance() {
        if (instance == null)
            instance = new SearchController();
        return instance;
    }
}
