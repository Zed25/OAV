package model;

import java.util.Base64;
import java.util.List;

/**
 * Created by simone on 22/06/17.
 */
public class Instrument {
    String name, satellite, map;
    List<Band> bands;

    public Instrument(String name, String satellite, String map, List<Band> bands) {
        this.name = name;
        this.satellite = satellite;
        this.map = map;
        this.bands = bands;
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

    public List<Band> getBands() {
        return bands;
    }

    public void setBands(List<Band> bands) {
        this.bands = bands;
    }
}
