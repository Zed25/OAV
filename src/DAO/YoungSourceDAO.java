package DAO;

import com.sun.rowset.CachedRowSetImpl;
import enumerations.ConnectionType;
import java.sql.*;

/**
 * Created by dilettalagom on 02/07/17.
 */
public class YoungSourceDAO extends SuperDAO {

    public CachedRowSetImpl searchYoungSource(int clumpID) {

       String query = "";
               /*"SELECT sources.sourceid " +
                "FROM sources INNER JOIN collection ON (sources.sourceid=collection.source)" +
                "NATURAL JOIN ellipses INNER JOIN clumps ON (ellipses.clump=clumps.clumpid)" +
                "WHERE (sqrt((sources.galacticlatitude - clumps.galacticlatitude)^2 +" +
                "(sources.galacticlongitude - clumps.galacticlongitude)^2) <" +
                "(ellipses.maxaxis * ellipses.band) AND (clumps.clumpid = ?) AND (ellipses.band = ?) );";*/

        Connection connection = connect(ConnectionType.COMPQUERY);

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
}
