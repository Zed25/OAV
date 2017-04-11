package DAO;

import beans.login.InstrumentBean;
import enumerations.ConnectionType;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by simone on 11/04/17.
 */
public class InstrumentDAO extends SuperDAO{


    public boolean serializeInstrument(InstrumentBean instrumentBean) {

        String query = "INSERT INTO instruments(name, satallite, starmap) VALUES ('" +
                instrumentBean.getName() + "', '" + instrumentBean.getSatellite() +
        "', '" + instrumentBean.getMap() + "')";

        System.out.println(query);

        Connection connection = connect(ConnectionType.COMPQUERY);

        try {
            Statement statement = connection.createStatement();

            statement.executeUpdate(query);

            String instrumentBandConnectionQuery;
            String instrumentName = instrumentBean.getName();
            for (int i = 0; i < instrumentBean.getBands().size(); i++){
                instrumentBandConnectionQuery = "INSERT INTO s_bsurbay(instrument, band) VALUES ('" +
                        instrumentName + "', '" + instrumentBean.getBands().get(i) + "')";
                System.out.println(instrumentBandConnectionQuery);

                statement.executeUpdate(instrumentBandConnectionQuery);
            }

            connection.commit();
            statement.close();
            disconnect(connection);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            finally {
                disconnect(connection);
                return false;
            }
        }

    }
}
