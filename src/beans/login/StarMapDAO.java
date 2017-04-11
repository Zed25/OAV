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
public class StarMapDAO extends SuperDAO{


    public List<String> getAllStarMapsNameFromDB() {

        String query = "SELECT Name From starmaps";

        Connection connection = connect(ConnectionType.SINGLEQUERY);

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            CachedRowSetImpl cachedRowSet = new CachedRowSetImpl();
            cachedRowSet.populate(resultSet);
            resultSet.close();
            statement.close();
            disconnect(connection);

            List<String> names = new ArrayList<>();

            while (cachedRowSet.next()){
                names.add(cachedRowSet.getString("Name"));
            }

            if(names.size() == 0)
                return null;

            return names;
        } catch (SQLException e) {
            e.printStackTrace();
            disconnect(connection);
            return null;
        }
    }
}
