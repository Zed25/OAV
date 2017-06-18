package Controllers;

import DAO.AgencyDAO;
import DAO.SatelliteDAO;
import beans.login.AgencyBean;
import beans.login.SatelliteBean;
import beans.login.UserBean;
import enumerations.ErrorType;
import model.Agency;
import model.Satellite;
import model.User;

import javax.jws.soap.SOAPBinding;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by simone on 03/06/17.
 */
public class SerializeSatelliteController {

    private static SerializeSatelliteController serializeSatelliteControllerinstance = null;

    private SerializeSatelliteController() {
    }

    public ErrorType serializeSatellite(SatelliteBean satelliteBean, UserBean userBean) {

        List<Agency> agenciesLinked = createAgenciesLinkedList(satelliteBean.getAgenciesLinked());
        User user = new User(userBean.getName(), userBean.getSurname(), userBean.getUserID(), userBean.getPassword(),
                userBean.getEmail(), userBean.getType());
        if(user.getType().equals("Admin")){
            user.setAdministrationRole();
        }

        return insertNewSatelliteInDB(satelliteBean.getName(), satelliteBean.getStartMissionDate(),
                satelliteBean.getEndMissionDate(), agenciesLinked, user);
    }

    /**
     * creates the agenciesConnected list from the srting filled by the jsp page
     */
    public List<Agency> createAgenciesLinkedList(String agenciesLinked) {
        List<Agency> agenciesList = new ArrayList<>();

        String[] agencies = agenciesLinked.split(",");

        for(int i = 0; i < agencies.length; i++){
            if(!agencies[i].equals("")){ //insert a new agency in the agenciesLinked list
                Agency agency = new Agency(agencies[i]);
                agenciesList.add(agency);
                System.out.println("Agency " + agency.getName() + " added to mission's list"); //DEBUG
            }
        }

        return agenciesList;
    }

    /**@param user who asks for**/
    public ErrorType insertNewSatelliteInDB(String name, String startMissionDate,
                                            String endMissionDate, List<Agency> agenciesLinked, User user){

        if (!user.isAdmin())
            return ErrorType.NO_ADMIN;

        List<Agency> newAgencies = getNewAgenciesFromSatelliteBean(agenciesLinked);
        Satellite satellite = new Satellite(name, startMissionDate, endMissionDate, agenciesLinked);

        SatelliteDAO satelliteDAO = new SatelliteDAO();

        //check start mission date and end mission date format (it must be "dd/mm/yyyy")
        String startMissionDateFormatted = formatStringDatetoDB(satellite.getStartMissionDate());
        String endMissionDateFormatted = formatStringDatetoDB(satellite.getEndMissionDate());
        if(startMissionDateFormatted == null || endMissionDateFormatted == null)
            return ErrorType.GEN_ERR;

        satellite.setStartMissionDate(startMissionDateFormatted);
        satellite.setEndMissionDate(endMissionDateFormatted);


        return satelliteDAO.serializeSatellite(satellite, newAgencies);

    }

    private String formatStringDatetoDB(String dateString) {

        String[] strings = dateString.split(" ");

        int day = Integer.parseInt(strings[0]);
        int month = -1;
        int year = Integer.parseInt(strings[2]);
        switch (strings[1]){
            case "January,":
                month = Integer.parseInt("00");
                break;
            case "February,":
                month = Integer.parseInt("01");;
                break;
            case "March,":
                month = Integer.parseInt("02");;
                break;
            case "April,":
                month = Integer.parseInt("03");;
                break;
            case "May,":
                month = Integer.parseInt("04");;
                break;
            case "June,":
                month = Integer.parseInt("05");;
                break;
            case "July,":
                month = Integer.parseInt("06");;
                break;
            case "August,":
                month = Integer.parseInt("07");;
                break;
            case "September,":
                month = Integer.parseInt("08");;
                break;
            case "October,":
                month = Integer.parseInt("09");;
                break;
            case "November,":
                month = Integer.parseInt("10");;
                break;
            case "December,":
                month = Integer.parseInt("11");;
                break;
        }

        if(month == -1){
            return null;
        }

        GregorianCalendar calendar = new GregorianCalendar(year, month, day);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setCalendar(calendar);
        String dateFormatted = format.format(calendar.getTime());
        System.out.println(dateFormatted); //DEBUG
        dateFormatted += " 00:00:00";
        System.out.println(dateFormatted); //DEBUG
        return dateFormatted;

    }

    private List<Agency> getNewAgenciesFromSatelliteBean(List<Agency> agencies) {
        AgencyDAO agencyDAO = new AgencyDAO();
        List<Agency> agenciesStored = agencyDAO.getAll();
        List<Agency> newAgencies = new ArrayList<>();

        boolean checkExistance = false;
        for(int i = 0; i < agencies.size(); i++){
            for (int j = 0; j < agenciesStored.size(); j++){
                if(agencies.get(i).getName().equals(agenciesStored.get(j).getName())){
                    checkExistance = true;
                    break;
                }
            }
            if(!checkExistance) {
                newAgencies.add(agencies.get(i));
                System.out.println("Agency " + agencies.get(i).getName() + " added to newAgencies"); //DEBUG
            }
            checkExistance = false;
        }

        if(newAgencies.size() == 0)
            return null;

        return newAgencies;
    }

    public static synchronized SerializeSatelliteController getSerializeSatelliteControllerinstance() {
        if(serializeSatelliteControllerinstance == null)
            serializeSatelliteControllerinstance = new SerializeSatelliteController();
        return serializeSatelliteControllerinstance;
    }
}
