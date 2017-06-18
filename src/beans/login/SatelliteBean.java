package beans.login;

import Controllers.SerializeController;
import Controllers.SerializeSatelliteController;
import enumerations.ErrorType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by simone on 03/04/17.
 */
public class SatelliteBean {
    String name, startMissionDate, endMissionDate, agenciesLinked;
    List<AgencyBean> agencyPartecipationList;

    /**
     * Satellite bean's constructor
     */
    public SatelliteBean(){
        name = "";
        startMissionDate = "";
        endMissionDate = "";
        agencyPartecipationList = null;
    }

    /**
     * check if the bean is full
     * if one of its attributes is void this method return false
     * @return boolean
     */
    public boolean isFull(){
        if(this.getName().equals("") ||
                this.getStartMissionDate().equals("") ||
                this.getAgenciesLinked().equals(""))
            return false;
        return true;
    }

    /**
     * sets the bean empty
     */
    public void emptyBean(){
        this.setName("");
        this.setStartMissionDate("");
        this.setEndMissionDate("");
        this.setAgenciesLinked("");
        if(this.getAgencyPartecipationList() != null)
            this.agencyPartecipationList = null;
    }

    /**
     * calls the constructor and pass itself for serialization
     * @param userBean who asks for
     * @return boolean
     */
    public ErrorType serializeSatellite(UserBean userBean){
        return SerializeSatelliteController.getSerializeSatelliteControllerinstance().serializeSatellite(this, userBean);
    }


    /**GETTER AND SETTER**/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartMissionDate() {
        return startMissionDate;
    }

    public void setStartMissionDate(String startMissionDate) {
        this.startMissionDate = startMissionDate;
    }

    public String getEndMissionDate() {
        return endMissionDate;
    }

    public void setEndMissionDate(String endMissionDate) {
        this.endMissionDate = endMissionDate;
    }

    public String getAgenciesLinked() {
        return agenciesLinked;
    }

    public void setAgenciesLinked(String agenciesLinked) {
        this.agenciesLinked = agenciesLinked;
    }

    public List<AgencyBean> getAgencyPartecipationList() {
        return agencyPartecipationList;
    }

    public void setAgencyPartecipationList(List<AgencyBean> agencyPartecipationList) {
        this.agencyPartecipationList = agencyPartecipationList;
    }
}
