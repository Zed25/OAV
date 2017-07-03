package DAO;

import com.sun.rowset.CachedRowSetImpl;
import enumerations.ConnectionType;
import java.sql.*;

/**
 * Created by dilettalagom on 02/07/17.
 */
public class YoungSourceDAO extends SuperDAO {

    public CachedRowSetImpl searchYoungSource(int clumpID) {

        /*String query=("CREATE VIEW V1 AS" +
                "        SELECT f1.source AS V1source, (f1.value-f2.value) AS difvalue4558 " +
                "        FROM fluxes AS f1 INNER JOIN fluxes AS f2 ON f1.source=f2.source " +
                "        WHERE ( ((f1.band=4.5) OR (f2.band=5.8)) AND ((f1.value-f2.value)>0.7) ); " +
                "      CREATE VIEW V2 AS" +
                "        SELECT f1.source AS V2source, (f1.value-f2.value) AS difvalue3645" +
                "        FROM fluxes AS f1 INNER JOIN fluxes AS f2 ON f1.source=f2.source" +
                "        WHERE ( ((f1.band=3.6) OR (f2.band=4.5)) AND ((f1.value-f2.value)>0.7) ); " +*/
                String query=( "      SELECT DISTINCT V1.V1source" +
                "        FROM V1 INNER JOIN V2 ON (V1.V1source=V2.V2source) " +
                "                INNER JOIN s_c_membership ON (V1.V1source=s_c_membership.source)" +
                "        WHERE ((V2.difvalue3645 > 1.4*(V1.difvalue4558 -0.7)+0.15) AND (s_c_membership.clump = ?) );");


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
