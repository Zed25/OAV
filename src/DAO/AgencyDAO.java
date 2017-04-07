package DAO;

import beans.login.AgencyBean;
import com.sun.rowset.CachedRowSetImpl;
import enumerations.ConnectionType;

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

    public List<AgencyBean> getAll(){
        String query = "SELECT * FROM Agencies;";

        System.out.println(query); //DEBUG

        List<AgencyBean> agencyBeanList = new ArrayList<>();

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
                    AgencyBean agencyBean = new AgencyBean();
                    agencyBean.setName(cachedRowSet.getString("Name"));
                    System.out.println(agencyBean.getName());
                    agencyBeanList.add(agencyBean);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }

        return agencyBeanList;
    }

    /*public void serializeAgency(AgencyBean agencyBean) {
        String query = "INSERT INTO Agencies (Name) VALUES ('" + agencyBean.getName() + "');";

        System.out.println(query); //DEBUG

        DataBaseManager.getInstance().insertTuple(query);
    }*/
}
