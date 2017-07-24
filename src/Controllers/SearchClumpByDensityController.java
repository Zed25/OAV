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
public class SearchClumpByDensityController {

    private static SearchClumpByDensityController searchClumpByDensityController;

    private SearchClumpByDensityController() {}

    public ErrorType searchPossibleClumpHosts(List<ClumpBean> clumpBeans){

        List<Clump> searchResults = getClumpsByDensity(0.1F, 1.0F);

        if(searchResults == null || searchResults.size() == 0)
            return ErrorType.GEN_ERR;

        for(int i = 0; i < searchResults.size(); i++){
            ClumpBean clumpBean = new ClumpBean();
            clumpBean.setClumpID(searchResults.get(i).getClumpID());
            clumpBean.setDensity(searchResults.get(i).getDensity());
            clumpBean.setPercPop(searchResults.get(i).getPercPop());
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

        double totalSources, percentage;

        totalSources = clumpDAO.getTableEntryNumber("sources");
        System.out.println(totalSources);

        cachedRowSet = clumpDAO.getSourcesPerClumpByDencity(minD, maxD);

        try {
            while(cachedRowSet.next()){
                for (int i = 0; i < clumps.size(); i++){
                    if(clumps.get(i).getClumpID() == cachedRowSet.getInt("clumpid")){
                        System.out.println(cachedRowSet.getInt("count"));
                        percentage = cachedRowSet.getInt("count")/totalSources;
                        clumps.get(i).setPercPop(percentage);
                        break;
                    }
                }
            }
            return clumps;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static synchronized SearchClumpByDensityController getSearchClumpByDensityController() {
        if (searchClumpByDensityController == null)
            searchClumpByDensityController = new SearchClumpByDensityController();
        return searchClumpByDensityController;
    }
}
