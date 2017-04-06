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
    private List<Integer> band;
    private List<Double> values;

    public ResultBean() {
        sources = new ArrayList<>();
        band = new ArrayList<>();
        values = new ArrayList<>();
    }

    public void setSources(List<String> sources) {
        this.sources = sources;
    }

    public void setBand(List<Integer> band) {
        this.band = band;
    }

    public void setValues(List<Double> values) {
        this.values = values;
    }

    public List<String> getSources() { return sources; }

    public List<Integer> getBand() { return band; }

    public List<Double> getValues() { return values; }

    public void populate(CachedRowSetImpl result) {
        if (this.getSources() == null) {
            this.setSources(new  ArrayList<String>());
            this.setBand(new  ArrayList<Integer>());
            this.setValues(new  ArrayList<Double>());
        }

        try {
            int count = 0;
            while (result.next()){
                System.out.println(result.getString("IDSorgente"));
                System.out.println(result.getDouble("Valore"));
                this.getSources().add(result.getString("IDSorgente"));
                this.getValues().add(result.getDouble("Valore"));
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
