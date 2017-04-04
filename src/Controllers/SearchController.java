package Controllers;

import DAO.AgencyDAO;
import beans.login.AgencyBean;

import java.util.List;

/**
 * Created by andrea on 29/03/17.
 */

public class SearchController {
    private static SearchController instance;

    private SearchController() {
    }

    // non dovra essere void, ma dovra' ritornare una lista di oggetti o un bean che li contiene

    public void FindObjectInMap(String mapName, int band){  //band==0 -> 1 banda, else tutte
        //Map map = new Map(mapName);
        //map.searchObject(band);


    }

    public List<AgencyBean> getAllAgencies() {
        AgencyDAO agencyDAO = new AgencyDAO();
        return agencyDAO.getAll();
    }

    public static synchronized SearchController getInstance() {
        if (instance == null)
            instance = new SearchController();
        return instance;
    }
}
