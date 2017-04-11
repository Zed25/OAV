package beans.login;

import DAO.SuperDAO;
import com.sun.rowset.CachedRowSetImpl;
import enumerations.ConnectionType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by simone on 11/04/17.
 */
public class BandDAO extends SuperDAO{

    public List<String> getAllBandsFromDB() {
        String query = "SELECT resolution FROM bands;";

        Connection connection = connect(ConnectionType.SINGLEQUERY);

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            CachedRowSetImpl cachedRowSet = new CachedRowSetImpl();
            cachedRowSet.populate(resultSet);
            resultSet.close();
            statement.close();
            disconnect(connection);

            List<String> bands = new ArrayList<>();
            while (cachedRowSet.next()){
                bands.add(cachedRowSet.getString("Resolution"));
            }

            if(bands.size() == 0)
                return null;

            return bands;
        } catch (SQLException e) {
            e.printStackTrace();
            disconnect(connection);
            return null;
        }
    }
}
