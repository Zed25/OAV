package model;

/**
 * Created by andrea on 08/06/17.
 */
public class Source {

    private String sourceID, comparedSource;
    private double galLat, galLong, distance;
    private float band, fluxValue;

    public Source() {
    }

    public Source(String sourceID, double galLong, double galLat, String comparedSource){
        this.sourceID=sourceID;
        this.galLong=galLong;
        this.galLat=galLat;
        this.comparedSource=comparedSource;
    }

    public Source(String sourceID, double galLong, double galLat){
        this.sourceID=sourceID;
        this.galLong=galLong;
        this.galLat=galLat;
    }

    public float getBand() { return band; }

    public void setBand(float band) { this.band = band; }

    public float getFluxValue() { return fluxValue; }

    public void setFluxValue(float fluxValue) { this.fluxValue = fluxValue; }

    public String getSourceID() {
        return sourceID;
    }

    public void setSourceID(String sourceID) {
        this.sourceID = sourceID;
    }

    public String getComparedSource() {
        return comparedSource;
    }

    public void setComparedSource(String comparedSource) {
        this.comparedSource = comparedSource;
    }

    public Double getGalLat() {
        return galLat;
    }

    public void setGalLat(double galLat) {
        this.galLat = galLat;
    }

    public Double getGalLong() {
        return galLong;
    }

    public void setGalLong(double galLong) {
        this.galLong = galLong;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
