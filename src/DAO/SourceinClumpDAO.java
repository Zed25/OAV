package DAO;

import DAO.SuperDAO;
import com.sun.rowset.CachedRowSetImpl;
import enumerations.ConnectionType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by dilettalagom on 30/06/17.
 */
public class SourceinClumpDAO extends SuperDAO{

    public CachedRowSetImpl searchSource(int clumpID, Float band) {


        String query = "SELECT sources.sourceid " +
                        "FROM sources INNER JOIN collection ON (sources.sourceid=collection.source) " +
                        "NATURAL JOIN ellipses INNER JOIN clumps ON (ellipses.clump=clumps.clumpid) " +
                        "WHERE (sqrt((sources.galacticlatitude - clumps.galacticlatitude)^2 +" +
                        "(sources.galacticlongitude - clumps.galacticlongitude)^2) <" +
                        "(ellipses.maxaxis * ellipses.band) AND (clumps.clumpid = ?) AND (ellipses.band = ?) " +
                        "AND (collection.starmap= 'MIPS-GAL')" +
                        "AND clumps.galacticlongitude>0 AND clumps.galacticlatitude>0 " +
                        "AND sources.galacticlongitude>0 AND sources.galacticlatitude>0 );";

        Connection connection = connect(ConnectionType.COMPQUERY);

        try {
            CachedRowSetImpl result = new CachedRowSetImpl();
            PreparedStatement statementInsert = connection.prepareStatement(query);
            statementInsert.setInt(1, clumpID);
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
    }
}
