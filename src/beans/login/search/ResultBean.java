package beans.login.search;

import Controllers.SearchController;
import com.sun.rowset.CachedRowSetImpl;

import java.sql.SQLException;

/**
 * Created by andrea on 31/03/17.
 */
public class ResultBean {

    private String[] sources;
    private int[] band;
    private double[] values;

    public ResultBean() { }

    public String[] getSources() { return sources; }

    public void setSources(String source, int pos) { this.sources[pos] = source; }

    public int[] getBand() { return band; }

    public void setBand(int[] band) { this.band = band; }

    public double[] getValues() { return values; }

    public void setValues(double values, int pos) { this.values[pos] = values; }

    public void populate(CachedRowSetImpl result) {
        try {
            int count = 0;
            while (result.next()){
                this.setSources(result.getString("Sorgente"), count);
                this.setValues(result.getDouble("Valore"), count);
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
