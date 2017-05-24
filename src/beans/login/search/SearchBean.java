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
    private int clumpID;

    public SearchBean() {
        this.mapName = "";
        this.allBands = false;
        this.bandRead = "0.0";
        this.realBand = 0.0f;
        this.clumpID = 0;
    }

    public int getClumpID() { return clumpID; }

    public void setClumpID(int clumpID) { this.clumpID = clumpID; }

    public String getBandRead() { return bandRead; }

    public void setBandRead(String bandRead) { this.bandRead = bandRead; }

    public Float getRealBand() { return realBand; }

    public void setRealBand(Float realBand) { this.realBand = realBand; }

    public String getMapName() { return mapName; }

    public void setMapName(String mapName) { this.mapName = mapName; }

    public boolean isAllBands() { return allBands; }

    public void setAllBands(boolean allBands) { this.allBands = allBands; }

    public void dropAllData() {
        this.setMapName("");
        this.setAllBands(false);
        this.setBandRead("0.0");
        this.setRealBand(0.0f);
        this.setClumpID(0);
    }

    public boolean isFullSource() {
        if (this.getMapName() != "") {
            return true;
        }
        else return false;
    }

    public boolean isFullClump() {
        if (this.getClumpID() != 0) {
            return true;
        }
        else return false;
    }

    public boolean findObjectInMap(SearchBean bean, ResultBean resBean) {
        this.setRealBand(Float.parseFloat(this.bandRead));
        return SearchController.getInstance().FindObjectInMap(this, resBean);
    }

    public boolean findClumpByID(SearchBean bean, ResultBean resBean) {
        return SearchController.getInstance().findClumpByID(this, resBean);
    }

    public List<String> getAllBandsFromDB() {
        List<String> bands = SerializeController.getSerializeControllerInstance().getAllBandsFromDB();
        bands.add(bands.size(), "All");
        return bands;
    }

    public boolean getMassAllClumps(ResultBean resBean) {
        return SearchController.getInstance().getMassAllClumps(resBean);
    }

    public boolean ratioBetweenLines(ResultBean resBean) {
        return SearchController.getInstance().ratioBetweenLines(resBean);
    }

    public List<String> getAllStarMapsNameFromDB() {
        return SerializeController.getSerializeControllerInstance().getAllStarMapsNameFromDB();
    }
}
