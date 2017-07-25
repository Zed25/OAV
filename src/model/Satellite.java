package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by simone on 03/06/17.
 */
public class Satellite {

    private String name, startMissionDate, endMissionDate;
    private List<Agency> agenciesLinked;

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

    //ricava Durata massima della missione
    public long maxMission(String startMissionDate, String endMissionDate){

        long diff = 0;
        try {
            Date d1 = new SimpleDateFormat("yyyy-M-dd").parse(startMissionDate);
            Date d2 = new SimpleDateFormat("yyyy-M-dd").parse(endMissionDate);
            diff= Math.abs(d1.getTime() - d2.getTime());
            //long diffDays = diff / (24 * 60 * 60 * 1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return diff;

    }
}
