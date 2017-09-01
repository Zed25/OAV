package DAO;

import com.sun.rowset.CachedRowSetImpl;
import enumerations.ConnectionType;
import java.sql.*;

/**
 * Created by dilettalagom on 02/07/17.
 */
public class YoungSourceDAO extends SuperDAO {

    public CachedRowSetImpl searchYoungSource(int clumpID) {

        /*UC_11 query: works with the Views created in OAVDB_creator.sql file*/
        String query=( "SELECT DISTINCT V1.V1source" +
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
