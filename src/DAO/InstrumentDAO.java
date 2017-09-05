package DAO;

import beans.login.InstrumentBean;
import enumerations.ConnectionType;
import enumerations.ErrorType;
import model.Agency;
import model.Instrument;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by simone on 11/04/17.
 */
public class InstrumentDAO extends SuperDAO{


    public ErrorType serializeInstrument(Instrument instrument) {

        String query = "INSERT INTO instruments(name, satallite, starmap) VALUES (?,?,?)";

        System.out.println(query);

        Connection connection = connect(ConnectionType.COMPQUERY);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, instrument.getName());
            preparedStatement.setString(2, instrument.getSatellite());
            preparedStatement.setString(3, instrument.getMap());

            preparedStatement.executeUpdate();

            preparedStatement.close();

            String instrumentBandConnectionQuery = "INSERT INTO s_bsurbay(instrument, band) VALUES (?,?)";
            String instrumentName = instrument.getName();
            preparedStatement = connection.prepareStatement(instrumentBandConnectionQuery);
            for (int i = 0; i < instrument.getBands().size(); i++){

                preparedStatement.setString(1, instrumentName);
                preparedStatement.setDouble(2, instrument.getBands().get(i).getResolution());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();

            connection.commit();
            preparedStatement.close();
            disconnect(connection);
            return ErrorType.NO_ERR;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            finally {
                disconnect(connection);
                return ErrorType.GEN_ERR;
            }
        }

    }

    public boolean deleteInstrumentAndConnections
            (String instrumentID){

        String deleteInstrumentConnections = "DELETE FROM s_bsurbay WHERE instrument = ?;";
        String deleteInstrument = "DELETE FROM instruments WHERE name = ?";

        Connection connection = connect(ConnectionType.SINGLEQUERY);
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(deleteInstrumentConnections);
            preparedStatement.setString(1, instrumentID);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            preparedStatement = connection.prepareStatement(deleteInstrument);
            preparedStatement.setString(1, instrumentID);
            preparedStatement.executeUpdate();
            preparedStatement.close();

        }catch (SQLException e) {
            e.printStackTrace();
            disconnect(connection);
            return false;
        }

        disconnect(connection);
        return true;
    }
}
