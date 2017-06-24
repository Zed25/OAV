package DAO;

import beans.login.search.SearchBean;
import beans.login.squareCircleSearchBean;
import com.sun.rowset.CachedRowSetImpl;
import enumerations.ConnectionType;

import java.sql.*;

/**
 * Created by simone on 29/04/17.
 */
public class ClumpDAO extends SuperDAO{

    public CachedRowSetImpl getMassAllClumps() {
        String query = "SELECT clumpid, temperature, value FROM clumps " +
                "INNER JOIN fluxes ON (clumps.clumpid = fluxes.clump) " +
                "WHERE (band = 350) AND (temperature != 0);";
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

    public CachedRowSetImpl searchClumpByID(int clumpID) {

        String query;

        query = "SELECT clumpid, galacticlatitude, galacticlongitude, band, value " +
                "FROM clumps INNER JOIN fluxes ON (clumps.clumpid = fluxes.clump) " +
                "WHERE (clumpid = ?);";
        Connection connection = connect(ConnectionType.SINGLEQUERY);
        try {
            CachedRowSetImpl result = new CachedRowSetImpl();
            PreparedStatement statementInsert = connection.prepareStatement(query);
            statementInsert.setInt(1, clumpID);
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

    public CachedRowSetImpl getClumpsByDensity(float minD, float maxD) {
        String query = "SELECT clumpid, surfacedensity FROM clumps WHERE (surfacedensity > ? AND surfacedensity < ?);";
        Connection connection = connect(ConnectionType.SINGLEQUERY);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setFloat(1, minD);
            preparedStatement.setFloat(2, maxD);

            ResultSet resultSet = preparedStatement.executeQuery();

            CachedRowSetImpl cachedRowSet = new CachedRowSetImpl();
            cachedRowSet.populate(resultSet);
            resultSet.close();
            preparedStatement.close();
            disconnect(connection);
            return cachedRowSet;
        } catch (SQLException e) {
            e.printStackTrace();
            disconnect(connection);
            return null;
        }
    }

    public CachedRowSetImpl getClumpsByGalacticRange(double[] latRange, double[] longRange) {
        String query = "SELECT clumpid, galacticlatitude, galacticlongitude FROM clumps " +
                "WHERE (galacticlatitude >= ? AND  galacticlatitude <= ?) " +
                "AND ( galacticlongitude >= ? AND galacticlongitude <= ?);";
        Connection connection = connect(ConnectionType.SINGLEQUERY);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDouble(1, latRange[0]);
            preparedStatement.setDouble(2, latRange[1]);
            preparedStatement.setDouble(3, longRange[0]);
            preparedStatement.setDouble(4, longRange[1]);
            ResultSet resultSet = preparedStatement.executeQuery();
            CachedRowSetImpl cachedRowSet = new CachedRowSetImpl();
            cachedRowSet.populate(resultSet);
            resultSet.close();
            preparedStatement.close();
            disconnect(connection);
            return cachedRowSet;
        } catch (SQLException e) {
            e.printStackTrace();
            disconnect(connection);
            return null;
        }
    }

    public CachedRowSetImpl executeQueryMODIFIED(String query) {    //MODIFIED

        Connection connection = connect(ConnectionType.SINGLEQUERY);
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            CachedRowSetImpl cachedRowSet = new CachedRowSetImpl();
            cachedRowSet.populate(resultSet);
            resultSet.close();
            statement.close();
            disconnect(connection);
            return cachedRowSet;
        } catch (SQLException e) {
            e.printStackTrace();
            disconnect(connection);
            return null;
        }
    }
}
