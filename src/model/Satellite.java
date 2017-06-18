package model;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by simone on 03/06/17.
 */
public class Satellite {

    String name, startMissionDate, endMissionDate;
    List<Agency> agenciesLinked;

    public Satellite(String name, String startMissionDate, String endMissionDate, List<Agency> agenciesLinked) {
        this.name = name;
        this.startMissionDate = startMissionDate;
        this.endMissionDate = endMissionDate;
        this.agenciesLinked = agenciesLinked;
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

    public List<Agency> getAgenciesLinked() {
        return agenciesLinked;
    }

    public void setAgenciesLinked(List<Agency> agenciesLinked) {
        this.agenciesLinked = agenciesLinked;
    }
}
