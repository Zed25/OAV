package Controllers;

import DAO.ClumpDAO;
import beans.login.ClumpBean;
import beans.login.ResultBean;
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

    public ErrorType searchPossibleClumpHosts(List<ClumpBean> clumpBeans, ResultBean resultBean){

        List<Clump> searchResults = new ArrayList<>();

        double percClumpPop = getClumpsByDensity(0.1F, 1.0F, searchResults);

        if(percClumpPop == 0)
            return ErrorType.GEN_ERR;


        resultBean.setPercClumpPop(percClumpPop);


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

    private double getClumpsByDensity(float minD, float maxD, List<Clump> clumps) {
        ClumpDAO clumpDAO = new ClumpDAO();
        CachedRowSetImpl cachedRowSet = clumpDAO.getClumpsByDensity(minD, maxD);

        try {
            while(cachedRowSet.next()){
                Clump clump = new Clump(cachedRowSet.getInt("clumpid"),
                        cachedRowSet.getFloat("surfacedensity"));
                clumps.add(clump);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            clumps = null;
            return 0;
        }

        if(clumps.size() == 0) {
            clumps = null;
            return 0;
        }

        double totalSources, totalClumps, sourcesPopulationPercentage, clumpPopulationPercentage;

        totalSources = clumpDAO.getTableEntryNumber("sources");
        totalClumps = clumpDAO.getClumpsTotalPopulation();
        //System.out.println(totalClumps);

        clumpPopulationPercentage = clumps.size()/totalClumps;
        cachedRowSet = clumpDAO.getSourcesPerClumpByDensity(minD, maxD);

        try {
            while(cachedRowSet.next()){
                for (int i = 0; i < clumps.size(); i++){
                    if(clumps.get(i).getClumpID() == cachedRowSet.getInt("clumpid")){
                        //System.out.println(cachedRowSet.getInt("count"));
                        sourcesPopulationPercentage = cachedRowSet.getInt("count")/totalSources;
                        clumps.get(i).setPercPop(sourcesPopulationPercentage);
                        break;
                    }
                }
            }
            return clumpPopulationPercentage;
        } catch (SQLException e) {
            e.printStackTrace();
            clumps =  null;
            return 0;
        }
    }

    public static synchronized SearchClumpByDensityController getSearchClumpByDensityController() {
        if (searchClumpByDensityController == null)
            searchClumpByDensityController = new SearchClumpByDensityController();
        return searchClumpByDensityController;
    }
}
