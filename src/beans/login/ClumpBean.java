package beans.login;

import Controllers.SearchClumpByDencityController;
import Controllers.SearchController;
import com.sun.beans.util.Cache;
import enumerations.ErrorType;

import java.lang.reflect.Array;
import java.math.BigDecimal;
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
    private float temperature;
    private double mass;
    private double percPop; //percentage of the total sources population


    public ClumpBean() {

    }

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

    public float getTemperature() { return temperature; }

    public void setTemperature(float temperature) { this.temperature = temperature; }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public double getPercPop() {
        return percPop;
    }

    public void setPercPop(double percPop) {
        this.percPop = percPop;
    }
}
