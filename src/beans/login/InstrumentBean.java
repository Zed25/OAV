package beans.login;

import Controllers.SerializeController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by simone on 11/04/17.
 */
public class InstrumentBean {
    String name, satellite, map, bandsFromFormString;
    List<String> bands;

    public InstrumentBean() {
        this.name = null;
        this.satellite = null;
        this.map = null;
        this.bandsFromFormString = null;
        this.bands = new ArrayList<>();
    }

    public boolean isFull() {
        if(this.getName() == null || this.getSatellite() == null || this.getMap() == null || this.getBandsFromFormString() == null)
            return false;

        return true;
    }

    public void emptyBean(){
        this.setName(null);
        this.setSatellite(null);
        this.setMap(null);
        this.setBandsFromFormString(null);
        this.bands = new ArrayList<>();
    }

    public List<String> getAllSatellitesNameFromDB() {
        return SerializeController.getSerializeControllerInstance().getAllSatellitesNameFromDB();
    }

    public List<String> getAllStarMapsNameFromDB() {
        return SerializeController.getSerializeControllerInstance().getAllStarMapsNameFromDB();
    }

    public List<String> getAllBandsFromDB() {
        return SerializeController.getSerializeControllerInstance().getAllBandsFromDB();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSatellite() {
        return satellite;
    }

    public void setSatellite(String satellite) {
        this.satellite = satellite;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getBandsFromFormString() {
        return bandsFromFormString;
    }

    public void setBandsFromFormString(String bandsFromFormString) {
        this.bandsFromFormString = bandsFromFormString;
    }

    public List<String> getBands() {
        return bands;
    }

    public void setBands(List<String> bands) {
        this.bands = bands;
    }
}
