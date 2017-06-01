package beans.login.search;

import Controllers.SearchController;
import beans.login.ClumpBean;
import beans.login.SourceBean;
import com.sun.beans.util.Cache;
import com.sun.rowset.CachedRowSetImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by andrea on 31/03/17.
 */
public class ResultBean {

    private List<ClumpBean> clumpBeans;
    private List<SourceBean> sourceBeans;

    //serve a tenere traccia del numero di risultati per pagina
    private int count;
    //serve a tenere traccia del numeo di pagina
    private int page;
    //UC 10
    private double mediumValue;
    private double standardDeviation;
    private double median;
    private double MAD;

    public ResultBean() {

    }

    public double getMediumValue() {
        return mediumValue;
    }

    public void setMediumValue(double mediumValue) {
        this.mediumValue = mediumValue;
    }

    public double getStandardDeviation() {
        return standardDeviation;
    }

    public void setStandardDeviation(double standardDeviation) {
        this.standardDeviation = standardDeviation;
    }

    public double getMedian() {
        return median;
    }

    public void setMedian(double median) {
        this.median = median;
    }

    public double getMAD() {
        return MAD;
    }

    public void setMAD(double MAD) {
        this.MAD = MAD;
    }

    public List<ClumpBean> getClumpBeans() { return clumpBeans; }

    public void setClumpBeans(List<ClumpBean> clumpBeans) { this.clumpBeans = clumpBeans; }

    public List<SourceBean> getSourceBeans() { return sourceBeans; }

    public void setSourceBeans(List<SourceBean> sourceBeans) { this.sourceBeans = sourceBeans; }

    public int getCount() { return count; }

    public void setCount(int count) { this.count = count; }

    public int getPage() { return page; }

    public void setPage(int page) { this.page = page; }

    public void reset() {
        this.count = 0;
        this.page = 1;
    }

    public void dropAllData() {
        this.reset();
        List<ClumpBean> clumpBeans = new ArrayList<>();
        this.setClumpBeans(clumpBeans);
        List<SourceBean> sourceBeans = new ArrayList<>();
        this.setSourceBeans(sourceBeans);
        this.setMediumValue(0.0);
        this.setMedian(0.0);
        this.setMAD(0.0);
        this.setStandardDeviation(0.0);
    }

    public void resetCount() { this.count = 0; }

    public void incrementCount () {
        this.count++;
    }

    public void incrementPage () { this.page++; }

    public void decrementPage () {
        if (this.page > 1) {
            if (this.count >= 50) {
                this.page--;
                this.count -= 50;
            }
        }
        else
            System.out.println("errore in indietro");   //TODO return error code
    }

    public void populateClumpsMass(CachedRowSetImpl result) {
        List<ClumpBean> clumps = new ArrayList<>();
        this.setClumpBeans(clumps);
        try {
            while (result.next()) {
                ClumpBean clump = new ClumpBean();
                clump.setClumpID(result.getInt("clumpid"));
                clump.setTemperature(result.getFloat("temperature"));
                clump.setFluxValue(result.getFloat("value"));
                clumps.add(clump);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void populateClumpsByID(CachedRowSetImpl result) {
        List<ClumpBean> clumps = new ArrayList<>();
        this.setClumpBeans(clumps);
        /*if (this.getClumpBeans().isEmpty()) {
            this.setClumpBeans(clumps);
        }*/
        try {
            while (result.next()){
                ClumpBean clump = new ClumpBean();
                clump.setClumpID(result.getInt("clumpid"));
                clump.setGalLat(result.getDouble("galacticlatitude"));
                clump.setGalLong(result.getDouble("galacticlongitude"));
                clump.setBand(result.getFloat("band"));
                clump.setFluxValue(result.getFloat("value"));
                clumps.add(clump);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void populateSourcesInMap(CachedRowSetImpl result, SearchBean bean) {
        List<SourceBean> sources = new ArrayList<>();
        List<ClumpBean> clumps = new ArrayList<>();
        this.setSourceBeans(sources);
        this.setClumpBeans(clumps);

        //Sources case
        if (!bean.getMapName().equals("HiGal")) {
            try {
                while (result.next()) {
                    SourceBean source = new SourceBean();
                    source.setSourceID(result.getString("sourceid"));
                    source.setBand(result.getFloat("band"));
                    source.setFluxValue(result.getFloat("value"));
                    sources.add(source);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        //Clumps case
        else {
            try {
                while ((result.next())) {
                    ClumpBean clump = new ClumpBean();
                    clump.setClumpID(result.getInt("clumpid"));
                    clump.setBand(result.getFloat("band"));
                    clump.setFluxValue(result.getFloat("value"));
                    clumps.add(clump);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
