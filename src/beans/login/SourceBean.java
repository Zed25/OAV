package beans.login;

/**
 * Created by simone on 06/05/17.
 */
public class SourceBean {

    private String sourceID;
    private double galLat, galLong, distance;
    private float band, fluxValue;

    public SourceBean() {
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
