package beans.login;

import Controllers.SearchController;

import java.util.List;

/**
 * Created by simone on 29/04/17.
 */
public class ClumpBean {

    private String clumpID, density, galLat, galLong;

    private double distance;

    public ClumpBean() {
    }

    public List<ClumpBean> getClumpsByDencity(float minD, float maxD){
        List<ClumpBean> clumps = SearchController.getInstance().getClumpsByDencity(minD, maxD);
        return clumps;
    }

    public String getClumpID() {
        return clumpID;
    }

    public void setClumpID(String clumpID) {
        this.clumpID = clumpID;
    }

    public String getDensity() {
        return density;
    }

    public void setDensity(String density) {
        this.density = density;
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
