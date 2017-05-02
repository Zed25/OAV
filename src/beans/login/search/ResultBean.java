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

    private List<String> sources;
    private List<Float> band;
    private List<Float> values;
    //serve a tenere traccia del numero di risultati per pagina
    private int count;
    //serve a tenere traccia del numeo di pagina
    private int page;

    public ResultBean() {
        sources = new ArrayList<>();
        band = new ArrayList<>();
        values = new ArrayList<>();
        this.count = 0;
        this.page = 1;
    }

    public void setSources(List<String> sources) {
        this.sources = sources;
    }

    public void setBand(List<Float> band) {
        this.band = band;
    }

    public void setValues(List<Float> values) {
        this.values = values;
    }

    public List<String> getSources() { return sources; }

    public List<Float> getBand() { return band; }

    public List<Float> getValues() { return values; }

    public int getCount() { return count; }

    public void setCount(int count) { this.count = count; }

    public int getPage() { return page; }

    public void setPage(int page) { this.page = page; }

    public void reset() {
        this.count = 0;
        this.page = 1;
    }

    public void resetCount() { this.count = 1; }

    public void incrementCount () {
        this.count += 1;
    }

    public void incrementPage () { this.page++; }

    public void decrementPage () {
        if (this.page > 0)
            this.page--;
        else;}

    public void populate(CachedRowSetImpl result) {
        if (this.getSources() == null) {
            this.setSources(new  ArrayList<String>());
            this.setBand(new  ArrayList<Float>());
            this.setValues(new  ArrayList<Float>());
        }

        try {
            int count = 0;
            while (result.next()){
                this.getSources().add(result.getString("IDSorgente"));
                this.getValues().add(result.getFloat("Valore"));
                this.getBand().add(result.getFloat("Risoluzione"));
                count ++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

/*    public void returnResults(ResultBean bean) {
        SearchController.getInstance().FindObjectInMap(this);

    }*/
}
