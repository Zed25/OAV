package beans.login.search;

import Controllers.SearchController;

/**
 * Created by andrea on 30/03/17.
 */
public class SearchBean {

    private String mapName;
    private boolean allBands;
    private int band;

    public SearchBean() {
        this.mapName = "";
        this.allBands = false;
        this.band = 0;
    }

    public String getMapName() { return mapName; }

    public void setMapName(String mapName) { this.mapName = mapName; }

    public boolean isAllBands() { return allBands; }

    public void setAllBands(boolean allBands) { this.allBands = allBands; }

    public int getBand() { return band; }

    public void setBand(int band) { this.band = band; }

    public boolean isFull() {
        if (this.getMapName() != "" || this.getBand() != 0) {
            return true;
        }
        else return false;
    }

   /* public void findObjectInMap(SearchBean bean, ResultBean resBean) {
        SearchController.getInstance().FindObjectInMap(this, resBean);

    }*/
}
