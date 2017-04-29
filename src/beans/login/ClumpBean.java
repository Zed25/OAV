package beans.login;

import Controllers.SearchController;

import java.util.List;

/**
 * Created by simone on 29/04/17.
 */
public class ClumpBean {

    private String clumpID, density;

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
}
