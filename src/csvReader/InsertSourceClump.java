package csvReader;

import DAO.SuperDAO;
import enumerations.ConnectionType;
import org.omg.CORBA.MARSHAL;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * Created by dilettalagom on 27/05/17.
 */
public class InsertSourceClump extends SuperDAO {

    Connection connection = connect(ConnectionType.COMPQUERY);

    private ArrayList<String[]> getSources() throws SQLException {
        ArrayList<String[]> tableSources = new ArrayList<String[]>();

        try {

            PreparedStatement statement = connection.prepareStatement
                    ("SELECT sourceid, galacticlatitude, galacticlongitude FROM sources INNER JOIN collection ON (sources.sourceid=collection.source)" +
                            "WHERE (collection.starmap = 'MIPS-GAL');");
            ResultSet res = statement.executeQuery();

            int columnCount = res.getMetaData().getColumnCount();

            while (res.next()) {

                String[] row = new String[columnCount];
                for (int i = 0; i < columnCount; i++) {
                    row[i] = res.getString(i + 1);
                }
                if (!((row[2]).equals("0") && row[1].equals("0")))
                    tableSources.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        /*TEST:for (String[] arr : tableSources) {
            System.out.println(Arrays.toString(arr));
        }*/

        return tableSources;

    }

    private ArrayList<String[]> getClumps() throws SQLException {

        ArrayList<String[]> tableClumps = new ArrayList<>();
        try {
            //Connection connection = connect(ConnectionType.SINGLEQUERY);
            PreparedStatement statement = connection.prepareStatement
                    ("SELECT ellipses.clump, clumps.galacticlatitude, clumps.galacticlatitude, ellipses.maxaxis, ellipses.band " +
                            "FROM ellipses INNER JOIN clumps ON (ellipses.clump=clumps.clumpid);");

            ResultSet res = statement.executeQuery();

            int columnCount = res.getMetaData().getColumnCount();

            while (res.next()) {

                String[] row = new String[columnCount];
                for (int i = 0; i < columnCount; i++) {
                    row[i] = res.getString(i + 1);
                }
                tableClumps.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        /* TEST: for (String[] arr : tableClumps) {
            System.out.println(Arrays.toString(arr));
        }*/

        return tableClumps;
    }

    //tableClumps (clump, galacticlatitude, galacticlongitude, maxaxis, band)
    //tableSource (sourceid, galacticlatitude, galacticlongitude)

    //formula =  double latitudine, double longitudine, double assex, double assey, double angolo


    //Un oggetto si dice che appartiene a un clump se
    // la distanza tra la posizione della sorgente  e quella del clump
    // è minore dell’asse maggiore dell’ellisse per la banda
   /* private ArrayList<String[]> membership(ArrayList<String[]> tableSources, ArrayList<String[]> tableClumps){

        ArrayList<String[]> SourceClump = new ArrayList<>();

        for (String[] clump : tableClumps){
            for (String[] source : tableSources) {
                if (Math.sqrt(Math.pow(Double.parseDouble(source[1])-Double.parseDouble(clump[1]),2)+
                        Math.pow(Double.parseDouble(source[2])-Double.parseDouble(clump[2]),2))
                        < (Double.parseDouble(clump[3])*Double.parseDouble(clump[4]))) {

                    SourceClump.add(new String[]{source[0], clump[0]});
                }
            }
        }
        return SourceClump;
    }

    public ArrayList<String[]> fillSCmemb() throws SQLException {

        //Passata la banda riempe la tabella di memebership
        ArrayList<String[]> tableClumps= new InsertSourceClump().getClumps();
        ArrayList<String[]> tableSources= new InsertSourceClump().getSources();

        ArrayList<String[]> result = membership(tableSources, tableClumps);
        try {
            Connection connection = new connect(ConnectionType.SINGLEQUERY);
            for (String[] res : result) {

                String query = "INSERT INTO s_c_membership(source, clump) Values ('" + res[0] + "'," + Integer.parseInt(res[1]) + ");";
                Statement statement = connection.createStatement();
                statement.executeUpdate(query);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
        //for (String[] arr : result) {
        //     System.out.println(Arrays.toString(arr));

        //  System.out.println("len di result " + result.size());

    }


}*/
    public boolean membership() throws SQLException {

        ArrayList<String[]> tableSources=getSources();
        ArrayList<String[]> tableClumps= null;
        try {
            tableClumps = getClumps();


            //ArrayList<String[]> SourceClump = new ArrayList<>();

            for (String[] clump : tableClumps) {
                for (String[] source : tableSources) {
                    if (Math.sqrt(Math.pow(Double.parseDouble(source[1]) - Double.parseDouble(clump[1]), 2) +
                            Math.pow(Double.parseDouble(source[2]) - Double.parseDouble(clump[2]), 2))
                            < (Double.parseDouble(clump[3]) * Double.parseDouble(clump[4]))) {

                        //SourceClump.add(new String[]{source[0], clump[0]});
                        //Connection connection = new connect(ConnectionType.SINGLEQUERY);
                        //for (String[] res : result) {

                            String query = "INSERT INTO s_c_membership(source, clump) Values ('" + source[0] + "'," + Integer.parseInt(clump[0]) + ");";
                            System.out.println(query);
                            Statement statement = connection.createStatement();
                            statement.executeUpdate(query);
                      //  }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}

