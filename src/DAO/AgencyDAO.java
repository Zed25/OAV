package DAO;

import beans.login.AgencyBean;
import com.sun.rowset.CachedRowSetImpl;
import enumerations.ConnectionType;
import enumerations.ErrorType;
import model.Agency;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by simone on 03/04/17.
 */
public class AgencyDAO extends SuperDAO{

    public List<Agency> getAll(){
        String query = "SELECT * FROM Agencies;";

        System.out.println(query); //DEBUG

        List<Agency> agencyList = new ArrayList<>();

        Connection connection = connect(ConnectionType.SINGLEQUERY);

        CachedRowSetImpl cachedRowSet;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            cachedRowSet = new CachedRowSetImpl();
            cachedRowSet.populate(resultSet);
            resultSet.close();
            statement.close();
            disconnect(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            disconnect(connection);
            return null;
        }

            try {
                while (cachedRowSet.next()){
                    Agency agency = new Agency(cachedRowSet.getString("Name"));
                    System.out.println(agency.getName()); //DEBUG
                    agencyList.add(agency);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }

        return agencyList;
    }

    /*public void serializeAgency(AgencyBean agencyBean) {
        String query = "INSERT INTO Agencies (Name) VALUES ('" + agencyBean.getName() + "');";

        System.out.println(query); //DEBUG

        DataBaseManager.getInstance().insertTuple(query);
    }*/
}
