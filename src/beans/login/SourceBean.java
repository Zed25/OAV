package beans.login;

/**
 * Created by simone on 06/05/17.
 */
public class SourceBean {

    private String sourceID, galLat, galLong;

    private double distance;

    public SourceBean() {
    }

    public String getSourceID() {
        return sourceID;
    }

    public void setSourceID(String sourceID) {
        this.sourceID = sourceID;
    }

    public String getGalLat() {
        return galLat;
    }

    public void setGalLat(String galLat) {
        this.galLat = galLat;
    }

    public String getGalLong() {
        return galLong;
    }

    public void setGalLong(String galLong) {
        this.galLong = galLong;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
