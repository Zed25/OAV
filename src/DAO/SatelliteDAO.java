package DAO;

import beans.login.AgencyBean;
import beans.login.InstrumentBean;
import beans.login.SatelliteBean;
import com.sun.rowset.CachedRowSetImpl;
import enumerations.ConnectionType;
import enumerations.ErrorType;
import model.Agency;
import model.Satellite;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by simone on 05/04/17.
 */
public class SatelliteDAO extends SuperDAO{


    public ErrorType serializeSatellite(Satellite satellite, List<Agency> newAgencies) {

        String checkSatelliteExistence = "SELECT Name FROM Satellites WHERE Name = ? ;";

        System.out.println(checkSatelliteExistence); //DEBUG

        Connection connection = connect(ConnectionType.COMPQUERY);
        CachedRowSetImpl cachedRowSetImpl;
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(checkSatelliteExistence);
            preparedStatement.setString(1, satellite.getName());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                resultSet.close();
                preparedStatement.close();
                disconnect(connection);
                System.out.println("Satellite's name already exists!"); //DEBUG
                return ErrorType.ALREADY_EXISTS;
            }
            preparedStatement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
            disconnect(connection);
            return ErrorType.GEN_ERR;
        }

        String query = "INSERT INTO Satellites (Name, StartMissionDate, EndMissionDate) VALUES (?,?,?);";

        System.out.println(query); //DEBUG

        System.out.println(satellite.getStartMissionDate());


        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, satellite.getName());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(satellite.getStartMissionDate()));
            if(satellite.getEndMissionDate() != null)
                preparedStatement.setTimestamp(3, Timestamp.valueOf(satellite.getEndMissionDate()));
            else
                preparedStatement.setTimestamp(3, null);
            preparedStatement.executeUpdate();
            preparedStatement.close();

            if(newAgencies != null) {
                String newAgencyQuery = "INSERT INTO Agencies (Name) VALUES (?);";
                preparedStatement = connection.prepareStatement(newAgencyQuery);
                for (int i = 0; i < newAgencies.size(); i++) {
                    preparedStatement.setString(1, newAgencies.get(i).getName());
                    System.out.println(query); //DEBUG
                    preparedStatement.addBatch();
                }
                preparedStatement.executeBatch();
                preparedStatement.close();
            }

            String agencyQuery = "INSERT INTO MissionsJoined (Agency, Satellite) VALUES (?,?);";
            preparedStatement = connection.prepareStatement(agencyQuery);
            for(int i = 0; i < satellite.getAgenciesLinked().size(); i++){
                preparedStatement.setString(1, satellite.getAgenciesLinked().get(i).getName());
                preparedStatement.setString(2, satellite.getName());
                preparedStatement.addBatch();
                System.out.println(agencyQuery); //DEBUG
            }
            preparedStatement.executeBatch();
            connection.commit();
            preparedStatement.close();
            disconnect(connection);
            return ErrorType.NO_ERR;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
                disconnect(connection);
            }
            disconnect(connection);
        }

        return ErrorType.GEN_ERR;
    }

    public List<String> getAllSatellitesNameFromDB() {

        String query = "SELECT Name From Satellites";

        Connection connection = connect(ConnectionType.SINGLEQUERY);

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            CachedRowSetImpl cachedRowSet = new CachedRowSetImpl();
            cachedRowSet.populate(resultSet);
            resultSet.close();
            statement.close();
            disconnect(connection);

            List<String> names = new ArrayList<>();

            while (cachedRowSet.next()){
                names.add(cachedRowSet.getString("Name"));
            }

            if(names.size() == 0)
                return null;

            return names;
        } catch (SQLException e) {
            e.printStackTrace();
            disconnect(connection);
            return null;
        }
    }


    public boolean deleteSatelliteAndNewAgenciesByID(String satelliteID, List<Agency> agenciesToDelete){

        String deleteFromMissionJoined = "DELETE FROM missionsjoined WHERE satellite = ?;";
        String deleteFromAgencies = "DELETE FROM agencies WHERE name = ?;";
        String deleteFromSatellite = "DELETE FROM satellites WHERE name = ?";

        Connection connection = connect(ConnectionType.SINGLEQUERY);
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(deleteFromMissionJoined);
            preparedStatement.setString(1, satelliteID);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            preparedStatement = connection.prepareStatement(deleteFromAgencies);
            for(int i = 0; i < agenciesToDelete.size(); i++) {
                preparedStatement.setString(1, agenciesToDelete.get(i).getName());
                preparedStatement.executeUpdate();
            }
            preparedStatement.close();
            preparedStatement = connection.prepareStatement(deleteFromSatellite);
            preparedStatement.setString(1, satelliteID);
            preparedStatement.executeUpdate();
            preparedStatement.close();

        }catch (SQLException e) {
            e.printStackTrace();
            disconnect(connection);
            return false;
        }

        disconnect(connection);
        return true;
    }
}
