package beans.login;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by simone on 03/04/17.
 */
public class SatelliteBean {
    String name, startMissionDate, endMissionDate;
    List<AgencyBean> agencyPartecipationList;

    public SatelliteBean(){
        name = "";
        startMissionDate = "";
        endMissionDate = "";
        agencyPartecipationList = null;
    }

    public void addAgencyToMission(AgencyBean agency){
        if(this.getAgencyPartecipationList() == null){
            this.setAgencyPartecipationList(new ArrayList<>());
        }
        this.getAgencyPartecipationList().add(agency);
        System.out.println("Agency " + agency.getName() + " added to mission's list"); //DEBUG
    }

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
}
