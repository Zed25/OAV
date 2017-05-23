package Controllers;

import DAO.*;
//import DAO.SearchDAO;
import beans.login.AgencyBean;
import beans.login.ClumpBean;
import beans.login.SourceBean;
import beans.login.search.ResultBean;
import beans.login.search.SearchBean;
import beans.login.squareCircleSearchBean;
import com.sun.rowset.CachedRowSetImpl;
import enumerations.ErrorType;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.math.*;

import static java.lang.Math.exp;

/**
 * Created by andrea on 29/03/17.
 */

public class SearchController {
    private static SearchController instance;

    private SearchController() {
    }

    //UC 4
    public boolean FindObjectInMap(SearchBean bean, ResultBean resBean) {  //band==0 -> 1 banda, else tutte

        SearchDAO dao = new SearchDAO();
        CachedRowSetImpl result = dao.searchObjectInMap(bean);
        resBean.populateSourcesInMap(result, bean);
        if (resBean.getClumpBeans().isEmpty() && resBean.getSourceBeans().isEmpty()) {
            return false;
        }
        else return true;
    }

    //UC 5
    public boolean findClumpByID(SearchBean bean, ResultBean resBean) {

        SearchDAO dao = new SearchDAO();
        CachedRowSetImpl result = dao.searchClumpByID(bean);
        resBean.populateClumpsByID(result);
        if (resBean.getClumpBeans().isEmpty()) {
            return false;
        }
        else return true;
    }

    //UC 10
    public boolean getMassAllClumps(ResultBean resBean) {
        ClumpDAO dao = new ClumpDAO();
        CachedRowSetImpl result = dao.getMassAllClumps();
        resBean.populateClumpsMass(result);
        if (resBean.getClumpBeans().isEmpty()) {
            return false;
        }
        else {
            for (ClumpBean clump : resBean.getClumpBeans()) {   //for item i in resbean.getClumpsBean
                clump.setMass(0.053*(1)*(10)*(exp(41.14/clump.getTemperature()) - 1));
                // where is 1 it should be the # of sources in the clump for band = 350us
            }
            return true;
        }
    }

    public List<AgencyBean> getAllAgencies(){
        AgencyDAO agencyDAO = new AgencyDAO();
        return agencyDAO.getAll();
    }

    public int getMaxClumpSourcesNumber() {
        int nClump, nSource;

        SuperDAO genericDAO = new ClumpDAO();

        nClump = genericDAO.getTableEntryNumber("clumps");
        nSource = genericDAO.getTableEntryNumber("sources");

        return Math.max(nClump, nSource);
    }

    public List<ClumpBean> getClumpsByDensity(float minD, float maxD) {
        ClumpDAO clumpDAO = new ClumpDAO();
        CachedRowSetImpl cachedRowSet = clumpDAO.getClumpsByDensity(minD, maxD);

        List<ClumpBean> clumps = new ArrayList<>();
        try {
            while(cachedRowSet.next()){
                ClumpBean clump = new ClumpBean();
                clump.setClumpID(cachedRowSet.getInt("clumpid"));
                clump.setDensity(cachedRowSet.getFloat("surfacedensity"));
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


    public ErrorType searchElementsInArea(squareCircleSearchBean squareCircleSearchBean) {
        switch (squareCircleSearchBean.getElementType()){
            case "Sources":
                return searchSourcesInArea(squareCircleSearchBean);
            case "Clumps":
                return searchClumpInArea(squareCircleSearchBean);
            default:
                return ErrorType.GEN_ERR;
        }
    }

    private ErrorType searchClumpInArea(squareCircleSearchBean squareCircleSearchBean) {

        String areaType = squareCircleSearchBean.getAreaType();

        //base coordinates and base length
        String baseGalLat = squareCircleSearchBean.getGalLat();
        String baseGalLong = squareCircleSearchBean.getGalLong();
        String baseLength = squareCircleSearchBean.getBaseLenght();

        double[] latRange = computeGalacticRange(areaType, baseGalLat, baseLength);
        double[] longRange = computeGalacticRange(areaType, baseGalLong, baseLength);

        System.out.println("latRange = [" + Double.toString(latRange[0]) + ", " + Double.toString(latRange[1]) + "]"); //DEBUG
        System.out.println("latRange = [" + Double.toString(longRange[0]) + ", " + Double.toString(longRange[1]) + "]"); //DEBUG

        ClumpDAO clumpDAO = new ClumpDAO();

        CachedRowSetImpl clumps = clumpDAO.getClumpsByGalacticRange(latRange, longRange);

        List<ClumpBean> clumpBeanList = new ArrayList<>(); //TODO manage clumps with threads

        try {
            while (clumps.next()) {
                if(Double.parseDouble(clumps.getString("galacticlatitude")) != 0 || Double.parseDouble(clumps.getString("galacticlongitude")) != 0) {
                    ClumpBean clump = new ClumpBean();
                    clump.setClumpID(clumps.getInt("clumpid"));
                    clump.setGalLat(clumps.getDouble("galacticlatitude"));
                    clump.setGalLong(clumps.getDouble("galacticlongitude"));
                    clumpBeanList.add(clump);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return ErrorType.GEN_ERR;
        }

        if(areaType.equals("Square")) {
            double lon, lat;
            for(int i = 0; i < clumpBeanList.size(); i++){
                lon = clumpBeanList.get(i).getGalLong() - Double.parseDouble(baseGalLong);
                lat = clumpBeanList.get(i).getGalLat() - Double.parseDouble(baseGalLat);
                clumpBeanList.get(i).setDistance(computeDistance(lon, lat));
            }
            sortClumpListByDistance(clumpBeanList);
            squareCircleSearchBean.setResultClumps(clumpBeanList); //if area type is a square all clumps in range are also in square area
            if(squareCircleSearchBean.getResultClumps().size() == 0)
                return ErrorType.NO_RESULTS;
            return ErrorType.NO_ERR;
        } else if(areaType.equals("Circle")){
            computeClumpIntersectionLineCircle(clumpBeanList, baseGalLat, baseGalLong, baseLength);
            sortClumpListByDistance(clumpBeanList);
            squareCircleSearchBean.setResultClumps(clumpBeanList);
            if(squareCircleSearchBean.getResultClumps().size() == 0)
                return ErrorType.NO_RESULTS;
            return ErrorType.NO_ERR;
        }

        return ErrorType.GEN_ERR;
    }

    private void sortClumpListByDistance(List<ClumpBean> clumpBeanList) {
        //a very stupid sort
        for(int i = 0; i < clumpBeanList.size(); i++){
            ClumpBean clumpBean = clumpBeanList.get(0);
            int pos = 0;
            for(int j = 1; j < clumpBeanList.size() - i; j++){
                if(clumpBeanList.get(j).getDistance() > clumpBean.getDistance()){
                    clumpBean = clumpBeanList.get(j);
                    pos = j;
                }

            }

            clumpBeanList.add(clumpBeanList.size() - i, clumpBean);
            clumpBeanList.remove(pos);

        }
    }

    private double computeDistance(double x, double y){
        return Math.sqrt((y*y) + (x*x));
    }

    private void computeClumpIntersectionLineCircle(List<ClumpBean> clumpBeanList, String baseGalLat, String baseGalLong, String baseLength) {

        //if distance between plan center and clump coordinates <= base length, the clump is in the area
        double y, x, i; //x and y cathetus, i hypotenuse
        for (int j = 0; j < clumpBeanList.size(); j++){
            ClumpBean clump = clumpBeanList.get(j);

            y = clump.getGalLat() - Double.parseDouble(baseGalLat);
            x = clump.getGalLong() - Double.parseDouble(baseGalLong);
            i = computeDistance(x, y);
            clump.setDistance(i);

            if(i > Double.parseDouble(baseLength)){
                clumpBeanList.remove(j);
            }

        }

    }

    private ErrorType searchSourcesInArea(squareCircleSearchBean squareCircleSearchBean) {
        String areaType = squareCircleSearchBean.getAreaType();

        //base coordinates and base length
        String baseGalLat = squareCircleSearchBean.getGalLat();
        String baseGalLong = squareCircleSearchBean.getGalLong();
        String baseLength = squareCircleSearchBean.getBaseLenght();

        double[] latRange = computeGalacticRange(areaType, baseGalLat, baseLength);
        double[] longRange = computeGalacticRange(areaType, baseGalLong, baseLength);

        System.out.println("latRange = [" + Double.toString(latRange[0]) + ", " + Double.toString(latRange[1]) + "]"); //DEBUG
        System.out.println("latRange = [" + Double.toString(longRange[0]) + ", " + Double.toString(longRange[1]) + "]"); //DEBUG

        SourceDAO sourceDAO = new SourceDAO();

        CachedRowSetImpl sources = sourceDAO.getSourcesByGalacticRange(latRange, longRange);

        List<SourceBean> sourceBeansList= new ArrayList<>(); //TODO manage sources with threads

        try {
            while (sources.next()) {
                if(Double.parseDouble(sources.getString("galacticlatitude")) != 0 || Double.parseDouble(sources.getString("galacticlongitude")) != 0) {
                    SourceBean sourceBean = new SourceBean();
                    sourceBean.setSourceID(sources.getString("sourceid"));
                    sourceBean.setGalLat(Double.parseDouble(sources.getString("galacticlatitude")));
                    sourceBean.setGalLong(Double.parseDouble(sources.getString("galacticlongitude")));
                    sourceBeansList.add(sourceBean);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return ErrorType.GEN_ERR;
        }

        if(areaType.equals("Square")) {
            double lon, lat;
            for(int i = 0; i < sourceBeansList.size(); i++){
                lon = sourceBeansList.get(i).getGalLong() - Double.parseDouble(baseGalLong);
                lat = sourceBeansList.get(i).getGalLat() - Double.parseDouble(baseGalLat);
                sourceBeansList.get(i).setDistance(computeDistance(lon, lat));
            }
            sortSourceListByDistance(sourceBeansList);
            squareCircleSearchBean.setResultSources(sourceBeansList); //if area type is a square all sources in range are also in square area
            if(squareCircleSearchBean.getResultSources().size() == 0)
                return ErrorType.NO_RESULTS;
            return ErrorType.NO_ERR;
        } else if(areaType.equals("Circle")){
            computeSourcesIntersectionLineCircle(sourceBeansList, baseGalLat, baseGalLong, baseLength);
            sortSourceListByDistance(sourceBeansList);
            squareCircleSearchBean.setResultSources(sourceBeansList);
            if(squareCircleSearchBean.getResultSources().size() == 0)
                return ErrorType.NO_RESULTS;
            return ErrorType.NO_ERR;
        }



        return ErrorType.GEN_ERR;
    }

    private void computeSourcesIntersectionLineCircle(List<SourceBean> sourceBeansList, String baseGalLat, String baseGalLong, String baseLength) {
        //if distance between plan center and clump coordinates <= base length, the clump is in the area
        double y, x, i; //x and y cathetus, i hypotenuse
        for (int j = 0; j < sourceBeansList.size(); j++){
            SourceBean sourceBean = sourceBeansList.get(j);

            y = sourceBean.getGalLat() - Double.parseDouble(baseGalLat);
            x = sourceBean.getGalLong() - Double.parseDouble(baseGalLong);
            i = computeDistance(x, y);
            sourceBean.setDistance(i);

            if(i > Double.parseDouble(baseLength)){
                sourceBeansList.remove(j);
            }

        }
    }

    private void sortSourceListByDistance(List<SourceBean> sourceBeansList) {
        //a very stupid insertion sort
        for(int i = 0; i < sourceBeansList.size(); i++){
            SourceBean sourceBean = sourceBeansList.get(0);
            int pos = 0;
            for(int j = 1; j < sourceBeansList.size() - i; j++){
                if(sourceBeansList.get(j).getDistance() > sourceBean.getDistance()){
                    sourceBean = sourceBeansList.get(j);
                    pos = j;
                }

            }

            sourceBeansList.add(sourceBeansList.size() - i, sourceBean);
            sourceBeansList.remove(pos);

        }
    }

    private double[] computeGalacticRange(String areaType, String galacticPoint, String base_length) {
        double min = 0.0, max = 0.0;
        switch (areaType){
            case "Square":
                min= Double.parseDouble(galacticPoint) - (Double.parseDouble(base_length)/2.0);
                max = Double.parseDouble(galacticPoint) + (Double.parseDouble(base_length)/2.0);
                break;
            case "Circle":
                min= Double.parseDouble(galacticPoint) - (Double.parseDouble(base_length));
                max = Double.parseDouble(galacticPoint) + (Double.parseDouble(base_length));
                break;
        }

        double[] range = {min, max};
        return range;
    }

    public static synchronized SearchController getInstance() {
        if (instance == null)
            instance = new SearchController();
        return instance;
    }
}
