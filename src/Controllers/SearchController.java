package Controllers;

import DAO.*;
//import DAO.SearchObjectDAO;
import beans.login.AgencyBean;
import model.Agency;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrea on 29/03/17.
 */

public class SearchController {
    private static SearchController instance;

    private SearchController() {
    }


    public List<AgencyBean> getAllAgencies(){
        AgencyDAO agencyDAO = new AgencyDAO();
        List<Agency> agencies = agencyDAO.getAll();

        if(agencies != null && agencies.size() != 0){
            List<AgencyBean> agencyBeanList = new ArrayList<>();
            for(int i = 0; i < agencies.size(); i++){
                AgencyBean agencyBean = new AgencyBean();
                agencyBean.setName(agencies.get(i).getName());
                agencyBeanList.add(agencyBean);
            }
            return agencyBeanList;
        }

        return null;
    }

    public int getMaxClumpSourcesNumber() {
        int nClump, nSource;

        SuperDAO genericDAO = new ClumpDAO();

        nClump = genericDAO.getTableEntryNumber("clumps");
        nSource = genericDAO.getTableEntryNumber("sources");

        return Math.max(nClump, nSource);
    }

    public static synchronized SearchController getInstance() {
        if (instance == null)
            instance = new SearchController();
        return instance;
    }
}
