package DAO;

import beans.login.SatelliteBean;
import com.sun.rowset.CachedRowSetImpl;

import java.sql.SQLException;

/**
 * Created by simone on 05/04/17.
 */
public class SatelliteDAO {


    public boolean serializeSatellite(SatelliteBean satelliteBean) {
        String checkSatelliteExistence = "SELECT Name FROM Satellites WHERE Name = '" + satelliteBean.getName() +"'";
        System.out.println(checkSatelliteExistence);

        CachedRowSetImpl cachedRowSetImpl = DataBaseManager.getInstance().dbQuery(checkSatelliteExistence);

        try {
            while(cachedRowSetImpl.next()){
                if(cachedRowSetImpl.getString("Name").equals(satelliteBean.getName()))
                    return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        String query = "INSERT INTO Satellites (Name, StartMissionDate, EndMissionDate) VALUES ('" + satelliteBean.getName() + "', '" +
                satelliteBean.getStartMissionDate() + "', '" + satelliteBean.getEndMissionDate() + "');";

        System.out.println(query); //DEBUG

        if(DataBaseManager.getInstance().insertTuple(query)){
            for(int i = 0; i < satelliteBean.getAgencyPartecipationList().size(); i++){
                if(addAgencyToSatelliteMission(satelliteBean.getName(), satelliteBean.getAgencyPartecipationList().get(i).getName()))
                    continue;
                else
                    return false;
            }
            return true;
        }

        return false;
    }

    private boolean addAgencyToSatelliteMission(String satelliteName, String agencyName) {
        String query = "INSERT INTO MissionsJoined (Agency, Satellite) VALUES ('" + agencyName +"','" +
                satelliteName + "');";

        System.out.println(query); //DEBUG

        if(DataBaseManager.getInstance().insertTuple(query))
            return true;
        return false;
    }
}
