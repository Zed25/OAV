package beans.login;

import Controllers.SerializeController;
import Controllers.SerializeInstrumentController;
import enumerations.ErrorType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by simone on 11/04/17.
 */
public class InstrumentBean {
    private String name, satellite, map, bandsFromFormString;
    private List<String> bands;

    /**
     * Instrument bean's constructor.
     * All attributes are set to null and the bands' array list is initialized
     */
    public InstrumentBean() {
        this.name = null;
        this.satellite = null;
        this.map = null;
        this.bandsFromFormString = null;
        this.bands = new ArrayList<>();
    }

    /**
     * check if the bean's object is full
     * @return boolean
     */
    public boolean isFull() {
        if(this.getName() == null || this.getSatellite() == null || this.getMap() == null || this.getBandsFromFormString() == null)
            return false;

        return true;
    }

    /**
     * clean the bean's object
     */
    public void emptyBean(){
        this.setName(null);
        this.setSatellite(null);
        this.setMap(null);
        this.setBandsFromFormString(null);
        this.bands = new ArrayList<>();
    }

    /**
     * asks serialize controller to get all satellites in db.
     * it returns only their name.
     * @return List of Strings
     */
    public List<String> getAllSatellitesNameFromDB() {
        return SerializeInstrumentController.getSerializeInstrumentControllerInstance().getAllSatellitesNameFromDB();
    }

    /**
     * asks serialize controller to get all star maps in db.
     * it returns only their name.
     * @return List of Strings
     */
    public List<String> getAllStarMapsNameFromDB() {
        return SerializeInstrumentController.getSerializeInstrumentControllerInstance().getAllStarMapsNameFromDB();
    }

    /**
     * asks serialize controller to get all bands in db.
     * it returns only their resolution as string.
     * @return List of Strings
     */
    public List<String> getAllBandsFromDB() {
        return SerializeInstrumentController.getSerializeInstrumentControllerInstance().getAllBandsFromDB();
    }

    /**GETTER AND SETTER**/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSatellite() {
        return satellite;
    }

    public void setSatellite(String satellite) {
        this.satellite = satellite;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getBandsFromFormString() {
        return bandsFromFormString;
    }

    /**
     * it converts a string of bands separated by a comma into a list of strings.
     * this list contains all bands managed by the instrument to be inserted.
     * @param bandsFromFormString
     */
    public void setBandsFromFormString(String bandsFromFormString) {
        this.bandsFromFormString = bandsFromFormString;

        if(bandsFromFormString != null) {

            if (this.getBands() == null) {
                this.bands = new ArrayList<>();
            }

            this.getBands().clear();

            String[] bands = this.getBandsFromFormString().split(",");

            for (int i = 0; i < bands.length; i++) {
                if (!bands[i].equals("")) {
                    this.getBands().add(bands[i]);
                }
            }
        }
    }

    public List<String> getBands() {
        return bands;
    }

    public void setBands(List<String> bands) {
        this.bands = bands;
    }
}
