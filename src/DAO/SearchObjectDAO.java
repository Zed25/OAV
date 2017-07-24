package DAO;

import beans.login.ClumpBean;
import beans.login.search.SearchBean;
import com.sun.rowset.CachedRowSetImpl;
import enumerations.ConnectionType;
import enumerations.ErrorType;

import java.sql.*;

/**
 * Created by andrea on 30/03/17.
 */
public class SearchObjectDAO extends SuperDAO {

    public CachedRowSetImpl searchObjectInMap(String mapName, float band) {

        String query;

        //Sources case

        if (!mapName.equals("HiGal")) {

            //voglio una banda precisa
            if ( band != 0.0f) {
                query = "SELECT fl.value, src.sourceid, fl.band " +
                        "FROM collection AS coll INNER JOIN sources AS src ON (coll.source = src.sourceid) " +
                        "INNER JOIN fluxes AS fl ON (coll.source = fl.source) " +
                        "WHERE (starmap = ? AND " +
                        "fl.band = ?);";
                Connection connection = connect(ConnectionType.SINGLEQUERY);
                try {
                    CachedRowSetImpl result = new CachedRowSetImpl();
                    PreparedStatement statementInsert = connection.prepareStatement(query);
                    statementInsert.setString(1, mapName);
                    statementInsert.setFloat(2, band);
                    ResultSet resultSet = statementInsert.executeQuery();
                    result.populate(resultSet);
                    statementInsert.close();
                    connection.close();
                    return result;
                } catch (SQLException e) {
                    e.printStackTrace();
                    disconnect(connection);
                    return null;
                }
            //voglio tutte le bande
            } else {
                query = "SELECT fl.value, src.sourceid, fl.band " +
                        "FROM collection AS coll INNER JOIN sources AS src ON (coll.source = src.sourceid) " +
                        "INNER JOIN fluxes AS fl ON (coll.source = fl.source) " +
                        "WHERE (starmap = ?);";
                Connection connection = connect(ConnectionType.SINGLEQUERY);
                try {
                    CachedRowSetImpl result = new CachedRowSetImpl();
                    PreparedStatement statementInsert = connection.prepareStatement(query);
                    statementInsert.setString(1, mapName);
                    ResultSet resultSet = statementInsert.executeQuery();
                    result.populate(resultSet);
                    statementInsert.close();
                    connection.close();
                    return result;
                } catch (SQLException e) {
                    e.printStackTrace();
                    disconnect(connection);
                    return null;
                }
            }
        }

        //Clump case

        else {
            //want a target band
            if ( band != 0.0f) {
                query = "SELECT value, clumpid, band " +
                        "FROM clumps INNER JOIN fluxes AS fl ON (clumps.clumpid = fl.clump) " +
                        "WHERE (band = ?);";
                Connection connection = connect(ConnectionType.SINGLEQUERY);
                try {
                    CachedRowSetImpl result = new CachedRowSetImpl();
                    PreparedStatement statementInsert = connection.prepareStatement(query);
                    statementInsert.setFloat(1, band);
                    ResultSet resultSet = statementInsert.executeQuery();
                    result.populate(resultSet);
                    statementInsert.close();
                    connection.close();
                    return result;
                } catch (SQLException e) {
                    e.printStackTrace();
                    disconnect(connection);
                    return null;
                }
            }
            //want all bands
            else {
                query = "SELECT value, clumpid, band " +
                        "FROM clumps INNER JOIN fluxes AS fl ON (clumps.clumpid = fl.clump)";
                Connection connection = connect(ConnectionType.SINGLEQUERY);
                try {
                    CachedRowSetImpl result = new CachedRowSetImpl();
                    PreparedStatement statementInsert = connection.prepareStatement(query);
                    ResultSet resultSet = statementInsert.executeQuery();
                    result.populate(resultSet);
                    statementInsert.close();
                    connection.close();
                    return result;
                } catch (SQLException e) {
                    e.printStackTrace();
                    disconnect(connection);
                    return null;
                }
            }
        }
    }
}