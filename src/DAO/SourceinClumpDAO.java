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


        String query = "SELECT s_c_membership.source " +
                "FROM s_c_membership INNER JOIN ellipses ON (s_c_membership.clump = ellipses.clump) " +
                "INNER JOIN collection ON (s_c_membership.source = collection.source) " +
                "WHERE ( (collection.starmap = 'MIPS-GAL') AND (s_c_membership.clump = ? ) AND (ellipses.band = ? ) )" +
                "ORDER BY s_c_membership.source ";

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
