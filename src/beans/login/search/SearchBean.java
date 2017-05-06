package beans.login.search;

import Controllers.SearchController;
import Controllers.SerializeController;

import java.util.List;

/**
 * Created by andrea on 30/03/17.
 */
public class SearchBean {

    private String mapName;
    private boolean allBands;
    private String bandRead;    //for reading from html
    private Float realBand;     //for SQL query

    public SearchBean() {
        this.mapName = "";
        this.allBands = false;
        this.bandRead = "0.0";
        this.realBand = 0.0f;
    }

    public String getBandRead() { return bandRead; }

    public void setBandRead(String bandRead) { this.bandRead = bandRead; }

    public Float getRealBand() { return realBand; }

    public void setRealBand(Float realBand) { this.realBand = realBand; }

    public String getMapName() { return mapName; }

    public void setMapName(String mapName) { this.mapName = mapName; }

    public boolean isAllBands() { return allBands; }

    public void setAllBands(boolean allBands) { this.allBands = allBands; }

    public boolean isFull() {
        if (this.getMapName() != "") {
            return true;
        }
        else return false;
    }

    public void findObjectInMap(SearchBean bean, ResultBean resBean) {
        this.setRealBand(Float.parseFloat(this.bandRead));
        SearchController.getInstance().FindObjectInMap(this, resBean);
    }

    public List<String> getAllBandsFromDB() {
        List<String> bands = SerializeController.getSerializeControllerInstance().getAllBandsFromDB();
        bands.add(bands.size(), "All");
        return bands;

    }

    public List<String> getAllStarMapsNameFromDB() {
        return SerializeController.getSerializeControllerInstance().getAllStarMapsNameFromDB();
    }
}
