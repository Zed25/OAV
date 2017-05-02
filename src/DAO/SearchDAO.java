package DAO;

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

    public CachedRowSetImpl searchObjectInMap(SearchBean bean) {

        String query;
        //voglio una banda precisa
        if (bean.getBand() != 0.0) {
            query = "SELECT f.\"Valore\", sorg.\"IDSorgente\", b.\"Risoluzione\" " +
                    "FROM \"Afferenza_M_S-S\" AS aff INNER JOIN \"Sorgenti\" AS sorg ON (aff.\"Sorgente\" = sorg.\"IDSorgente\") " +
                    "INNER JOIN \"Flussi\" AS f ON (aff.\"Sorgente\" = f.\"Sorgente\") " +
                    "INNER JOIN \"Bande\" AS b ON (f.\"Banda\" = b.\"IDBanda\") " +
                    "WHERE (\"MappaStellare\" = " + "'" + bean.getMapName() + "' AND " + "b.\"Risoluzione\" = " + "'" + bean.getBand() + "');";
            //voglio tutte le bande
        } else {
            query = "SELECT f.\"Valore\", sorg.\"IDSorgente\", b.\"Risoluzione\" " +
                    "FROM \"Afferenza_M_S-S\" AS aff INNER JOIN \"Sorgenti\" AS sorg ON (aff.\"Sorgente\" = sorg.\"IDSorgente\") " +
                    "INNER JOIN \"Flussi\" AS f ON (aff.\"Sorgente\" = f.\"Sorgente\") " +
                    "INNER JOIN \"Bande\" AS b ON (f.\"Banda\" = b.\"IDBanda\") " +
                    "WHERE (\"MappaStellare\" = " + "'" + bean.getMapName() + "');";
        }


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