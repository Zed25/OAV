package beans.login;

import Controllers.SearchController;
import com.sun.beans.util.Cache;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by simone on 29/04/17.
 */
public class ClumpBean {

    private int clumpID;
    private float density;
    private double galLat, galLong;
    private double distance;
    private float band;
    private float fluxValue;


    public ClumpBean() {

    }

    public List<ClumpBean> getClumpsByDencity(float minD, float maxD){
        List<ClumpBean> clumps = SearchController.getInstance().getClumpsByDencity(minD, maxD);
        return clumps;
    }

    //public Map<Float, Float> getBand_fluxValue() { return band_fluxValue; }

    //public void setBand_fluxValue(Map<Float, Float> band_fluxValue) { this.band_fluxValue = band_fluxValue; }


    public float getBand() { return band; }

    public void setBand(float band) { this.band = band; }

    public float getFluxValue() { return fluxValue; }

    public void setFluxValue(float fluxValue) { this.fluxValue = fluxValue; }

    public Integer getClumpID() {
        return clumpID;
    }

    public void setClumpID(Integer clumpID) { this.clumpID = clumpID; }

    public Float getDensity() { return density; }

    public void setDensity(Float density) {
        this.density = density;
    }

    public Double getGalLat() {
        return galLat;
    }

    public void setGalLat(Double galLat) {
        this.galLat = galLat;
    }

    public Double getGalLong() {
        return galLong;
    }

    public void setGalLong(Double galLong) {
        this.galLong = galLong;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
