package Controllers;

import DAO.*;
import beans.login.AgencyBean;
import beans.login.InstrumentBean;
import beans.login.SatelliteBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by simone on 05/04/17.
 */
public class SerializeController {

    private static SerializeController serializeControllerInstance = null;

    private SerializeController() {
    }



    public boolean serializeInstrument(InstrumentBean instrumentBean) {
        InstrumentDAO instrumentDAO = new InstrumentDAO();

        return instrumentDAO.serializeInstrument(instrumentBean);
    }

    public List<String> getAllSatellitesNameFromDB() {
        SatelliteDAO satelliteDAO = new SatelliteDAO();
        return satelliteDAO.getAllSatellitesNameFromDB();
    }

    public List<String> getAllStarMapsNameFromDB() {
        StarMapDAO starMapsDAO = new StarMapDAO();
        return starMapsDAO.getAllStarMapsNameFromDB();
    }

    public List<String> getAllBandsFromDB() {
        BandDAO bandDAO = new BandDAO();
        return bandDAO.getAllBandsFromDB();
    }
    //it will return a boolean
    /*private void serializeAgency(AgencyBean agencyBean) {
        AgencyDAO agencyDAO = new AgencyDAO();

        agencyDAO.serializeAgency(agencyBean);
    }*/

    public static synchronized SerializeController getSerializeControllerInstance() {
        if(serializeControllerInstance == null)
            serializeControllerInstance = new SerializeController();
        return serializeControllerInstance;
    }
}
