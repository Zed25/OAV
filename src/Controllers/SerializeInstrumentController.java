package Controllers;

import DAO.BandDAO;
import DAO.InstrumentDAO;
import DAO.SatelliteDAO;
import DAO.StarMapDAO;
import beans.login.AdministrationBean;
import beans.login.InstrumentBean;
import beans.login.SatelliteBean;
import beans.login.UserBean;
import enumerations.ErrorType;
import model.Administration;
import model.Band;
import model.Instrument;
import model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by simone on 22/06/17.
 */
public class SerializeInstrumentController {

    private static SerializeInstrumentController serializeInstrumentControllerinstance = null;

    private SerializeInstrumentController() {
    }

    public ErrorType serializeInstrument(AdministrationBean administrationBean, InstrumentBean instrumentBean){
        if(administrationBean == null){
            return ErrorType.NO_ADMIN;
        }

        List<Band> bands = new ArrayList<>();
        double resolution;
        for (int i = 0; i < instrumentBean.getBands().size(); i++){
            resolution = Double.parseDouble(instrumentBean.getBands().get(i));
            Band band = new Band(resolution);
            bands.add(band);
        }

        Instrument instrument = new Instrument(instrumentBean.getName(), instrumentBean.getSatellite(),
                instrumentBean.getMap(), bands);

        User user = new User(administrationBean.getName(), administrationBean.getSurname(), administrationBean.getUserID(),
                administrationBean.getPassword(), administrationBean.getEmail(), administrationBean.getType());

        if(user.getType().equals("Admin")){
            user.setAdministrationRole();
        }

        return insertInstrumentIntoDB(instrument, user.getAdministrationRole());

    }

    private ErrorType insertInstrumentIntoDB(Instrument instrument, Administration administration) {

        if(administration == null){
            return ErrorType.NO_ADMIN;
        }

        InstrumentDAO instrumentDAO = new InstrumentDAO();

        return instrumentDAO.serializeInstrument(instrument);
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


    public static synchronized SerializeInstrumentController getSerializeInstrumentControllerInstance() {
        if(serializeInstrumentControllerinstance == null)
            serializeInstrumentControllerinstance = new SerializeInstrumentController();
        return serializeInstrumentControllerinstance;
    }
}
