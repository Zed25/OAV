package DAO;

import beans.login.search.SearchBean;
import beans.login.squareCircleSearchBean;
import com.sun.rowset.CachedRowSetImpl;
import enumerations.ConnectionType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by simone on 29/04/17.
 */
public class ClumpDAO extends SuperDAO{

    public CachedRowSetImpl getMassAllClumps() {
        String query = "SELECT clumpid, temperature, value FROM clumps " +
                "INNER JOIN fluxes ON (clumps.clumpid = fluxes.clump) " +
                "WHERE (band = 350) AND (temperature != 0);";
        return executeQuery(query);
    }

    public CachedRowSetImpl getClumpsByDensity(float minD, float maxD) {
        String query = "SELECT clumpid, surfacedensity FROM clumps WHERE (surfacedensity > 0.1 AND surfacedensity < 1.0);";
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

    public CachedRowSetImpl getClumpsByGalacticRange(double[] latRange, double[] longRange) {
        String query = "SELECT clumpid, galacticlatitude, galacticlongitude FROM clumps WHERE (galacticlatitude >= " + latRange[0] + " AND  galacticlatitude <= " + latRange[1] + ") AND ( galacticlongitude >= " + longRange[0] +
                " AND galacticlongitude <= " + longRange[1] + ");";
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

    public CachedRowSetImpl executeQuery(String query) {

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
