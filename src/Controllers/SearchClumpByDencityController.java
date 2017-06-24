package Controllers;

import DAO.ClumpDAO;
import beans.login.ClumpBean;
import com.sun.rowset.CachedRowSetImpl;
import enumerations.ErrorType;
import model.Clump;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by simone on 24/06/17.
 */
public class SearchClumpByDencityController {

    private static SearchClumpByDencityController searchClumpByDencityController;

    private SearchClumpByDencityController() {}

    public ErrorType searchPossibleClumpHosts(List<ClumpBean> clumpBeans){

        List<Clump> searchResults = getClumpsByDensity(0.1F, 1.0F);

        if(searchResults == null || searchResults.size() == 0)
            return ErrorType.GEN_ERR;

        for(int i = 0; i < searchResults.size(); i++){
            ClumpBean clumpBean = new ClumpBean();
            clumpBean.setClumpID(searchResults.get(i).getClumpID());
            clumpBean.setDensity(searchResults.get(i).getDensity());
            clumpBeans.add(clumpBean);
        }

        return ErrorType.NO_ERR;
    }

    private List<Clump> getClumpsByDensity(float minD, float maxD) {
        ClumpDAO clumpDAO = new ClumpDAO();
        CachedRowSetImpl cachedRowSet = clumpDAO.getClumpsByDensity(minD, maxD);

        List<Clump> clumps = new ArrayList<>();
        try {
            while(cachedRowSet.next()){
                Clump clump = new Clump(cachedRowSet.getInt("clumpid"),
                        cachedRowSet.getFloat("surfacedensity"));
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

    public static synchronized SearchClumpByDencityController getSearchClumpByDencityController() {
        if (searchClumpByDencityController == null)
            searchClumpByDencityController = new SearchClumpByDencityController();
        return searchClumpByDencityController;
    }
}
