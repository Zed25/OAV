package beans.login;

import Controllers.SerializeController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by simone on 03/04/17.
 */
public class SatelliteBean {
    String name, startMissionDate, endMissionDate, agenciesConnected;
    boolean toSerialize;
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
        if(this.getName().equals("") || this.getStartMissionDate().equals("") || this.getAgenciesConnected().equals("") || this.getAgencyPartecipationList() == null)
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
        this.setAgenciesConnected("");
        if(this.getAgencyPartecipationList() != null)
            this.agencyPartecipationList = null;
    }

    /**
     * calls the constructor and pass itself for serialization
     * @return boolean
     */
    public boolean serializeSatellite(){
        return SerializeController.getSerializeControllerInstance().serializeSatellite(this);
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

    public List<AgencyBean> getAgencyPartecipationList() {
        return agencyPartecipationList;
    }

    public void setAgencyPartecipationList(List<AgencyBean> agencyPartecipationList) {
        this.agencyPartecipationList = agencyPartecipationList;
    }

    public String getAgenciesConnected() {
        return agenciesConnected;
    }

    /**
     * creates the agenciesConnected list from the srting filled by the jsp page
     */
    public void setAgenciesConnected(String agenciesConnected) {
        this.agenciesConnected = agenciesConnected;

        if(this.getAgencyPartecipationList() == null){
            this.agencyPartecipationList = new ArrayList<>();
        }

        this.getAgencyPartecipationList().clear();

        String[] agencies = this.getAgenciesConnected().split(",");

        for(int i = 0; i < agencies.length - 1; i++){
            if(!agencies[i].equals("")){
                addAgencyToMission(agencies[i]);
            }
        }
    }

    /**
     * insert a new agency in the agenciesConnected list
     * @param agencyName
     */
    public void addAgencyToMission(String agencyName){
        AgencyBean agency = new AgencyBean();
        agency.setName(agencyName);
        this.getAgencyPartecipationList().add(agency);
        System.out.println("Agency " + agency.getName() + " added to mission's list"); //DEBUG
    }
}
