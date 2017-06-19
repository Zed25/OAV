package model;

import beans.login.ClumpBean;

import static java.lang.Math.exp;

/**
 * Created by andrea on 01/06/17.
 */
public class Clump {

    private int clumpID;
    private float density;
    private double galLat, galLong, distance, mass;
    private float band, fluxValue, temperature;

    public Clump(){
    }

    public Clump(int clumpID, float temperature, float fluxValue) {
        this.clumpID = clumpID;
        this.temperature = temperature;
        this.fluxValue = fluxValue;
    }

    public double computeMass(Clump clump) {
        return 0.053*(clump.getFluxValue())*10*(exp(41.14/clump.getTemperature()) - 1);
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

}
