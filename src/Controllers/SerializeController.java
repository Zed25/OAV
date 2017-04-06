package Controllers;

import DAO.AgencyDAO;
import beans.login.AgencyBean;
import beans.login.SatelliteBean;
import DAO.SatelliteDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by simone on 05/04/17.
 */
public class SerializeController {

    private static SerializeController serializeControllerInstance = null;

    private SerializeController() {
    }

    public boolean serializeSatellite(SatelliteBean satelliteBean) {
        List<AgencyBean> newAgencies = getNewAgenciesFromSatelliteBean(satelliteBean.getAgencyPartecipationList());
        if(newAgencies != null){
            for(int i = 0; i < newAgencies.size(); i++){
                serializeAgency(newAgencies.get(i));
            }
        }

        SatelliteDAO satelliteDAO = new SatelliteDAO();

        if(satelliteDAO.serializeSatellite(satelliteBean))
            return true;

        return false;
    }

    private List<AgencyBean> getNewAgenciesFromSatelliteBean(List<AgencyBean> agencies) {
        AgencyDAO agencyDAO = new AgencyDAO();
        List<AgencyBean> agenciesStored = agencyDAO.getAll();
        List<AgencyBean> newAgencies = new ArrayList<>();

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

    //it will return a boolean
    private void serializeAgency(AgencyBean agencyBean) {
        AgencyDAO agencyDAO = new AgencyDAO();

        agencyDAO.serializeAgency(agencyBean);
    }

    public static synchronized SerializeController getSerializeControllerInstance() {
        if(serializeControllerInstance == null)
            serializeControllerInstance = new SerializeController();
        return serializeControllerInstance;
    }
}
