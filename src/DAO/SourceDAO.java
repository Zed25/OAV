package DAO;

import com.sun.rowset.CachedRowSetImpl;
import enumerations.ConnectionType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by simone on 02/05/17.
 */
public class SourceDAO extends SuperDAO{


    public CachedRowSetImpl getSourcesByGalacticRange(double[] latRange, double[] longRange) {
        String query = "SELECT sourceid, galacticlatitude, galacticlongitude FROM sources WHERE (galacticlatitude >= " + latRange[0] + " AND  galacticlatitude <= " + latRange[1] + ") AND ( galacticlongitude >= " + longRange[0] +
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
}
