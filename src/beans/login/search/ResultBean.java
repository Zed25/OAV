package beans.login.search;

import Controllers.SearchController;
import com.sun.rowset.CachedRowSetImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrea on 31/03/17.
 */
public class ResultBean {

    /*UC4*/
    private List<String> sources;
    private List<String> band;
    private List<Float> values;
    private List<Integer> clumps;
    //serve a tenere traccia del numero di risultati per pagina
    private int count;
    //serve a tenere traccia del numeo di pagina
    private int page;


    /*UC5*/
    private int clumpID;



    public ResultBean() {
        sources = new ArrayList<>();
        band = new ArrayList<>();
        values = new ArrayList<>();
        clumps = new ArrayList<>();
        this.count = 0;
        this.page = 1;
        this.clumpID = 0;
    }

    public int getClumpID() { return clumpID; }

    public void setClumpID(int clumpID) { this.clumpID = clumpID; }

    public List<Integer> getClumps() { return clumps; }

    public void setClumps(List<Integer> clumps) { this.clumps = clumps; }

    public void setSources(List<String> sources) {
        this.sources = sources;
    }

    public void setBand(List<String> band) {
        this.band = band;
    }

    public void setValues(List<Float> values) {
        this.values = values;
    }

    public List<String> getSources() { return sources; }

    public List<String> getBand() { return band; }

    public List<Float> getValues() { return values; }

    public int getCount() { return count; }

    public void setCount(int count) { this.count = count; }

    public int getPage() { return page; }

    public void setPage(int page) { this.page = page; }

    public void reset() {
        this.count = 0;
        this.page = 1;
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
        else;   //TODO return error code
    }

    public void populateClumpsByID(CachedRowSetImpl result) {
        if (this.getClumpID() == 0) {
            this.
        }

    }

    public void populateSourcesInMap(CachedRowSetImpl result, SearchBean bean) {
        if (this.getSources() == null) {
            this.setSources(new  ArrayList<String>());
            this.setBand(new  ArrayList<String>());
            this.setValues(new  ArrayList<Float>());
            this.setClumps(new  ArrayList<Integer>());
        }

        //Sources case
        if (!bean.getMapName().equals("HiGal")) {
            try {
                while (result.next()){
                    this.getSources().add(result.getString("sourceid"));
                    this.getValues().add(result.getFloat("value"));
                    this.getBand().add(result.getString("band"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        //Clumps case
        else {
            try {
                while ((result.next())){
                    this.getClumps().add(result.getInt("clumpid"));
                    this.getValues().add(result.getFloat("value"));
                    this.getBand().add(result.getString("band"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
