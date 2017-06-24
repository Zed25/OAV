package DAO;

import com.sun.rowset.CachedRowSetImpl;
import enumerations.ConnectionType;

import java.sql.*;

/**
 * Created by simone on 02/05/17.
 */
public class SourceDAO extends SuperDAO{


    public CachedRowSetImpl getSourcesByGalacticRange(double[] latRange, double[] longRange) {
        String query = "SELECT sourceid, galacticlatitude, galacticlongitude FROM sources " +
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
}
