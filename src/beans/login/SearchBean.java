package beans.login;

import Controllers.*;
import Controllers.SourceInClumpController;
import enumerations.ErrorType;

import java.util.List;

/**
 * Created by andrea on 30/03/17.
 */
public class SearchBean {

    private String mapName;
    private boolean allBands;
    private String band;    //for reading from html
    private Float realBand;     //for SQL query
    private int clumpID;
    private boolean resetflag;  //to avoid session bean issues

    public SearchBean() {
        this.band = "";
        this.mapName = "";
        this.resetflag = false;
    }

    public boolean isResetflag() {
        return resetflag;
    }

    public void setResetflag(boolean resetflag) {
        this.resetflag = resetflag;
    }

    public int getClumpID() { return clumpID; }

    public void setClumpID(int clumpID) { this.clumpID = clumpID; }

    public String getBand() { return band; }

    public void setBand(String bandRead) { this.band = bandRead; }

    public Float getRealBand() { return realBand; }

    public void setRealBand(Float realBand) { this.realBand = realBand; }

    public String getMapName() { return mapName; }

    public void setMapName(String mapName) { this.mapName = mapName; }

    public boolean isAllBands() { return allBands; }

    public void setAllBands(boolean allBands) { this.allBands = allBands; }

    public void dropAllData() {
        this.setMapName("");
        this.setAllBands(false);
        this.setBand("");
        this.setRealBand(0.0f);
        this.setClumpID(0);
        this.setResetflag(false);
    }

    public boolean isFullSourceinClump() {
        if (this.getClumpID() != 0 && this.getBand() != "" ) {
            return true;
        }
        else return false;
    }


    public boolean isFullSource() {
        if (this.getMapName() != "" && this.getBand() != "") {
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

    public ErrorType findYoungSuorce(SearchBean searchBean, ResultBean resultBean){
        return YoungSourceController.getInstance().getYoungSource(this, resultBean);
    }

    public ErrorType findClumpByID(SearchBean bean, ResultBean resBean) {
        return FindClumpByIDController.getInstance().findClumpByIDFromBean(this, resBean);
    }

    public ErrorType sourceInClump(SearchBean searchBean, ResultBean resultBean){
        return SourceInClumpController.getInstance().getElementsFromBean(searchBean,resultBean);
    }

    public ErrorType findObjectInMap(SearchBean bean, ResultBean resBean) {
        if (!this.getBand().equals("All"))
            this.setRealBand(Float.parseFloat(this.band));
        else
            this.setRealBand(0.0f);
        return SearchObjectInMapController.getInstance().FindObjectInMapFromBean(this, resBean);
    }

    public List<String> getAllBandsFromDB() {
        List<String> bands = SerializeController.getSerializeControllerInstance().getAllBandsFromDB();
        bands.add(bands.size(), "All");
        return bands;
    }

    public ErrorType getMassAllClumps(ResultBean resBean) {
        return GetMassAllClumpController.getInstance().getMassAllClumpsFromBean(resBean);
    }

    public ErrorType ratioBetweenLines(ResultBean resBean) {
        return RatioBetweenLinesController.getInstance().ratioBetweenLinesFromBean(resBean);
    }

    public List<String> getAllStarMapsNameFromDB() {
        return SerializeController.getSerializeControllerInstance().getAllStarMapsNameFromDB();
    }
}
