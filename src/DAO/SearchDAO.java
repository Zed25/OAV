package DAO;

import beans.login.ClumpBean;
import beans.login.search.SearchBean;
import com.sun.rowset.CachedRowSetImpl;
import enumerations.ConnectionType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by andrea on 30/03/17.
 */
public class SearchDAO extends SuperDAO {

    public CachedRowSetImpl searchClumpByID(SearchBean bean) {

        String query;

        query = "SELECT clumpid, galacticlatitude, galacticlongitude, band, value " +
                "FROM clumps INNER JOIN fluxes ON (clumps.clumpid = fluxes.clump) " +
                "WHERE (clumpid = " + "'" + bean.getClumpID() + "');";

        return executeQuery(query);
    }

    public CachedRowSetImpl searchObjectInMap(SearchBean bean) {

        String query;

        //Sources case

        if (!bean.getMapName().equals("HiGal")) {

            //voglio una banda precisa
            if ( bean.getRealBand() != 0.0f) {
                query = "SELECT fl.value, src.sourceid, fl.band " +
                        "FROM collection AS coll INNER JOIN sources AS src ON (coll.source = src.sourceid) " +
                        "INNER JOIN fluxes AS fl ON (coll.source = fl.source) " +
                        "WHERE (starmap = " + "'" + bean.getMapName() + "' AND " + "fl.band = " + "'" + bean.getRealBand() + "');";
            //voglio tutte le bande
            } else {
                query = "SELECT fl.value, src.sourceid, fl.band " +
                        "FROM collection AS coll INNER JOIN sources AS src ON (coll.source = src.sourceid) " +
                        "INNER JOIN fluxes AS fl ON (coll.source = fl.source) " +
                        "WHERE (starmap = " + "'" + bean.getMapName() + "');";
            }
        }

        //Clump case

        else {
            //want a target band
            if ( bean.getRealBand() != 0.0f) {
                query = "SELECT fl.value, clumps.clumpid, fl.band " +
                        "FROM clumps INNER JOIN fluxes AS fl ON (clumps.clumpid = fl.clump) " +
                        "WHERE (clumps.higalmaps = " + "'" + bean.getMapName() + "' AND " + "fl.band = " + "'" + bean.getRealBand() + "');";
            }
            //want all bands
            else {
                query = "SELECT fl.value, clumps.clumpid, fl.band " +
                        "FROM clumps INNER JOIN fluxes AS fl ON (clumps.clumpid = fl.clump) " +
                        "WHERE (clumps.higalmaps = " + "'" + bean.getMapName() + "');";
            }
        }
        return executeQuery(query);
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