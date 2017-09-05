package Controllers;

import DAO.ClumpDAO;
import DAO.SourceDAO;
import beans.login.ClumpBean;
import beans.login.SourceBean;
import beans.login.SquareCircleSearchBean;
import com.sun.rowset.CachedRowSetImpl;
import enumerations.ErrorType;
import model.Clump;
import model.Source;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by simone on 24/06/17.
 */
public class SearchInSquareOrCirclesController {
    private static SearchInSquareOrCirclesController searchInSquareOrCirclesController;

    private SearchInSquareOrCirclesController() {}

    /**check the bean and create the model object.
     * than search for the right element in the area selecter**/
    public ErrorType searchElementsInArea(SquareCircleSearchBean squareCircleSearchBean) {
        ErrorType errorType;

        //check the bean
        if(checkBean(squareCircleSearchBean) == ErrorType.BAD_VALUE)
            return ErrorType.BAD_VALUE;

        //get the right flux
        switch (squareCircleSearchBean.getElementType()){
            case "Sources":
                List<Source> sourcesResults = new ArrayList<>();
                errorType = searchSourcesInArea(sourcesResults, squareCircleSearchBean.getAreaType(),
                        Double.parseDouble(squareCircleSearchBean.getGalLat()),
                        Double.parseDouble(squareCircleSearchBean.getGalLong()),
                        Double.parseDouble(squareCircleSearchBean.getBaseLenght()));

                if (sourcesResults.size() == 0)
                    return ErrorType.NO_RESULTS;
                //create results bean
                List<SourceBean> sourceBeans = new ArrayList<>();
                for(int j = 0; j < sourcesResults.size(); j++){
                    SourceBean sourceBean = new SourceBean();
                    sourceBean.setSourceID(sourcesResults.get(j).getSourceID());
                    sourceBean.setDistance(sourcesResults.get(j).getDistance());
                    sourceBeans.add(sourceBean);
                }

                squareCircleSearchBean.setResultSources(sourceBeans);
                return errorType;

            case "Clumps":
                List<Clump> clumpsResults = new ArrayList<>();
                errorType = searchClumpInArea(clumpsResults, squareCircleSearchBean.getAreaType(),
                        Double.parseDouble(squareCircleSearchBean.getGalLat()),
                        Double.parseDouble(squareCircleSearchBean.getGalLong()),
                        Double.parseDouble(squareCircleSearchBean.getBaseLenght()));
                if(clumpsResults.size() == 0)
                    return ErrorType.NO_RESULTS;

                //create results bean
                List<ClumpBean> clumpBeans = new ArrayList<>();
                for(int i = 0; i < clumpsResults.size(); i++){
                    ClumpBean clumpBean = new ClumpBean();
                    clumpBean.setClumpID(clumpsResults.get(i).getClumpID());
                    clumpBean.setDistance(clumpsResults.get(i).getDistance());
                    clumpBeans.add(clumpBean);
                }
                squareCircleSearchBean.setResultClumps(clumpBeans);
                return errorType;
            default:
                return ErrorType.GEN_ERR;
        }
    }

    private ErrorType checkBean(SquareCircleSearchBean squareCircleSearchBean) {

        try {
            Double.valueOf(squareCircleSearchBean.getBaseLenght());
            Double.valueOf(squareCircleSearchBean.getGalLong());
            Double.valueOf(squareCircleSearchBean.getGalLat());
        }catch (NumberFormatException e){
            return ErrorType.BAD_VALUE;
        }

        return ErrorType.NO_ERR;
    }

    private ErrorType searchClumpInArea(List<Clump> clumpResults, String areaType, double baseGalLat, double baseGalLong, double baseLenght) {

        //compute galactic range between lat and base lenght and long and base lenght
        double[] latRange = computeGalacticRange(areaType, baseGalLat, baseLenght);
        double[] longRange = computeGalacticRange(areaType, baseGalLong, baseLenght);

        //System.out.println("latRange = [" + Double.toString(latRange[0]) + ", " + Double.toString(latRange[1]) + "]"); //DEBUG
        //System.out.println("latRange = [" + Double.toString(longRange[0]) + ", " + Double.toString(longRange[1]) + "]"); //DEBUG

        ClumpDAO clumpDAO = new ClumpDAO();

        //get clumps by galactic range
        CachedRowSetImpl clumps = clumpDAO.getClumpsByGalacticRange(latRange, longRange);

        try {
            while (clumps.next()) {
                if(Double.parseDouble(clumps.getString("galacticlatitude")) != 0 || Double.parseDouble(clumps.getString("galacticlongitude")) != 0) {
                    Clump clump = new Clump(clumps.getInt("clumpid"),
                            clumps.getDouble("galacticlatitude"), clumps.getDouble("galacticlongitude"));
                    clumpResults.add(clump);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return ErrorType.GEN_ERR;
        }

        //compute clumps in area
        if(areaType.equals("Square")) {
            double lon, lat;
            for(int i = 0; i < clumpResults.size(); i++){
                lon = clumpResults.get(i).getGalLong() - baseGalLong;
                lat = clumpResults.get(i).getGalLat() - baseGalLat;
                clumpResults.get(i).setDistance(computeDistance(lon, lat));
            }
            sortClumpListByDistance(clumpResults); //if area type is a square all clumps in range are also in square area
            if(clumpResults.size() == 0)
                return ErrorType.NO_RESULTS;
            return ErrorType.NO_ERR;
        } else if(areaType.equals("Circle")){
            computeClumpIntersectionLineCircle(clumpResults, baseGalLat, baseGalLong, baseLenght);
            sortClumpListByDistance(clumpResults);
            if(clumpResults.size() == 0)
                return ErrorType.NO_RESULTS;
            return ErrorType.NO_ERR;
        }

        return ErrorType.GEN_ERR;
    }

    private ErrorType searchSourcesInArea(List<Source> sourceResults, String areaType, double baseGalLat, double baseGalLong, double baseLenght) {


        //compute galactic range between lat and base lenght and long and base lenght
        double[] latRange = computeGalacticRange(areaType, baseGalLat, baseLenght);
        double[] longRange = computeGalacticRange(areaType, baseGalLong, baseLenght);

        //System.out.println("latRange = [" + Double.toString(latRange[0]) + ", " + Double.toString(latRange[1]) + "]"); //DEBUG
        //System.out.println("latRange = [" + Double.toString(longRange[0]) + ", " + Double.toString(longRange[1]) + "]"); //DEBUG

        SourceDAO sourceDAO = new SourceDAO();

        //get sources by galactic range
        CachedRowSetImpl sources = sourceDAO.getSourcesByGalacticRange(latRange, longRange);

        try {
            while (sources.next()) {
                if(Double.parseDouble(sources.getString("galacticlatitude")) != 0 || Double.parseDouble(sources.getString("galacticlongitude")) != 0) {
                    Source source = new Source(sources.getString("sourceid"),
                            Double.parseDouble(sources.getString("galacticlatitude")),
                            Double.parseDouble(sources.getString("galacticlongitude")));
                    sourceResults.add(source);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return ErrorType.GEN_ERR;
        }


        //compute sources in area
        if(areaType.equals("Square")) {
            double lon, lat;
            for(int i = 0; i < sourceResults.size(); i++){
                lon = sourceResults.get(i).getGalLong() - baseGalLong;
                lat = sourceResults.get(i).getGalLat() - baseGalLat;
                sourceResults.get(i).setDistance(computeDistance(lon, lat));
            }
            sortSourceListByDistance(sourceResults); //if area type is a square all sources in range are also in square area
            if(sourceResults.size() == 0)
                return ErrorType.NO_RESULTS;
            return ErrorType.NO_ERR;
        } else if(areaType.equals("Circle")){
            computeSourcesIntersectionLineCircle(sourceResults, baseGalLat, baseGalLong, baseLenght);
            sortSourceListByDistance(sourceResults);
            if(sourceResults.size() == 0)
                return ErrorType.NO_RESULTS;
            return ErrorType.NO_ERR;
        }



        return ErrorType.GEN_ERR;
    }


    /**compute galactic range
     * if the area is a square the base lenght must be divide by 2
     * this method returns the min and the max galactic point**/
    private double[] computeGalacticRange(String areaType, double galacticPoint, double base_length) {
        double min = 0.0, max = 0.0;
        switch (areaType){
            case "Square":
                min= galacticPoint - (base_length/2.0);
                max = galacticPoint + (base_length/2.0);
                break;
            case "Circle":
                min= galacticPoint - base_length;
                max = galacticPoint + base_length;
                break;
        }

        double[] range = {min, max};
        return range;
    }

    private double computeDistance(double x, double y){
        return Math.sqrt((y*y) + (x*x));
    }

    private void computeClumpIntersectionLineCircle(List<Clump> clumpList, double baseGalLat, double baseGalLong, double baseLength) {

        //if distance between plan center and clump coordinates <= base length, the clump is in the area
        double y, x, i; //x and y cathetus, i hypotenuse
        for (int j = 0; j < clumpList.size(); j++){
            Clump clump = clumpList.get(j);

            y = clump.getGalLat() - baseGalLat;
            x = clump.getGalLong() - baseGalLong;
            i = computeDistance(x, y);
            clump.setDistance(i);

            if(i > baseLength){
                clumpList.remove(j);
            }

        }

    }

    private void computeSourcesIntersectionLineCircle(List<Source> sourceList, double baseGalLat, double baseGalLong, double baseLength) {
        //if distance between plan center and clump coordinates <= base length, the clump is in the area
        double y, x, i; //x and y cathetus, i hypotenuse
        for (int j = 0; j < sourceList.size(); j++){
            Source source = sourceList.get(j);

            y = source.getGalLat() - baseGalLat;
            x = source.getGalLong() - baseGalLong;
            i = computeDistance(x, y);
            source.setDistance(i);

            if(i > baseLength){
                sourceList.remove(j);
            }

        }
    }

    private void sortClumpListByDistance(List<Clump> clumpList) {
        for(int i = 0; i < clumpList.size(); i++){
            Clump clump = clumpList.get(0);
            int pos = 0;
            for(int j = 1; j < clumpList.size() - i; j++){
                if(clumpList.get(j).getDistance() > clump.getDistance()){
                    clump = clumpList.get(j);
                    pos = j;
                }

            }

            clumpList.add(clumpList.size() - i, clump);
            clumpList.remove(pos);

        }
    }

    private void sortSourceListByDistance(List<Source> sourceList) {
        for(int i = 0; i < sourceList.size(); i++){
            Source source = sourceList.get(0);
            int pos = 0;
            for(int j = 1; j < sourceList.size() - i; j++){
                if(sourceList.get(j).getDistance() > source.getDistance()){
                    source = sourceList.get(j);
                    pos = j;
                }

            }

            sourceList.add(sourceList.size() - i, source);
            sourceList.remove(pos);

        }
    }

    public static synchronized SearchInSquareOrCirclesController getSearchInSquareOrCirclesController() {
        if (searchInSquareOrCirclesController == null)
            searchInSquareOrCirclesController = new SearchInSquareOrCirclesController();
        return searchInSquareOrCirclesController;
    }
}
