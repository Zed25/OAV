package model;

import beans.login.ClumpBean;

import static java.lang.Math.exp;
import static java.lang.Math.pow;

/**
 * Created by andrea on 01/06/17.
 */
public class Clump {

    private int clumpID, type;
    private float density;
    private double galLat, galLong, distance, mass, lmRatio;
    private float band, fluxValue, temperature;
    private double percPop; ////percentage of the total sources population


    public Clump(int clumpID, double galLong, double galLat, float temperature, double lmRatio, float density , int type){
        this.clumpID=clumpID;
        this.galLat=galLat;
        this.galLong=galLong;
        this.temperature=temperature;
        this.lmRatio=lmRatio;
        this.density=density;
        this.type=type;
    }

    public Clump(int clumpID, float temperature, float fluxValue) {
        this.clumpID = clumpID;
        this.temperature = temperature;
        this.fluxValue = fluxValue;

    }
    public Clump(int clumpID, float temperature, float fluxValue, int type) {
        this.clumpID = clumpID;
        this.temperature = temperature;
        this.fluxValue = fluxValue;
        this.type = type;

    }

    public Clump(int clumpID, double galLat, double galLong) {
        this.clumpID = clumpID;
        this.galLat = galLat;
        this.galLong = galLong;
    }

    public Clump(int clumpID, float density) {
        this.clumpID = clumpID;
        this.density = density;
    }

    public Clump(){

    }

    public double computeMass(Clump clump) {
        return 0.053*(clump.getFluxValue())*pow(10, 2)*(exp(41.14/clump.getTemperature()) - 1);
    }


    public Integer getType() { return type; }

    public void setType(Integer  type) { this.type=type;}

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

    public double getLmRatio() { return lmRatio;}

    public void setLmRatio(double lmRatio) { this.lmRatio = lmRatio; }

    public double getPercPop() {
        return percPop;
    }

    public void setPercPop(double percPop) {
        this.percPop = percPop;
    }
}
