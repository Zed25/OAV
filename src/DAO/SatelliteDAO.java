package DAO;

import beans.login.AgencyBean;
import beans.login.SatelliteBean;
import com.sun.rowset.CachedRowSetImpl;
import enumerations.ConnectionType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by simone on 05/04/17.
 */
public class SatelliteDAO extends SuperDAO{


    public boolean serializeSatellite(SatelliteBean satelliteBean, List<AgencyBean> newAgencies) {
        String checkSatelliteExistence = "SELECT Name FROM Satellites WHERE Name = '" + satelliteBean.getName() +"';";
        System.out.println(checkSatelliteExistence);

        Connection connection = connect(ConnectionType.COMPQUERY);
        CachedRowSetImpl cachedRowSetImpl;
        Statement statement;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(checkSatelliteExistence);
            if(resultSet.next()){
                resultSet.close();
                statement.close();
                disconnect(connection);
                System.out.println("Satellite's name already exists!");
                return false;
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
            disconnect(connection);
            return false;
        }

        String query = "INSERT INTO Satellites (Name, StartMissionDate, EndMissionDate) VALUES ('" + satelliteBean.getName() + "', '" +
                satelliteBean.getStartMissionDate() + "', '" + satelliteBean.getEndMissionDate() + "');";

        System.out.println(query); //DEBUG


        try {
            statement.executeUpdate(query);
            if(newAgencies != null) {
                for (int i = 0; i < newAgencies.size(); i++) {
                    String newAgencyQuery = "INSERT INTO Agencies (Name) VALUES ('" + newAgencies.get(i).getName() + "');";

                    System.out.println(query); //DEBUG

                    statement.executeUpdate(newAgencyQuery);
                }
            }
            for(int i = 0; i < satelliteBean.getAgencyPartecipationList().size(); i++){
                String agencyQuery = "INSERT INTO MissionsJoined (Agency, Satellite) VALUES ('" + satelliteBean.getAgencyPartecipationList().get(i).getName() +"','" +
                        satelliteBean.getName() + "');";

                System.out.println(agencyQuery); //DEBUG

                statement.executeUpdate(agencyQuery);
            }
            connection.commit();
            disconnect(connection);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.abort(null); //TODO check executor
            } catch (SQLException e1) {
                e1.printStackTrace();
                disconnect(connection);
            }
            disconnect(connection);
        }

        return false;
    }
}
