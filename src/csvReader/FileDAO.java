package csvReader;

import DAO.SuperDAO;
import enumerations.ConnectionType;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dilettalagom on 07/04/17.
 */


public class FileDAO extends SuperDAO {


    public boolean fillingTable(String fileName, List<String[]> allLines) {

        Connection connection = connect(ConnectionType.COMPQUERY);
        String[] tableNamesRight;

        switch (fileName) {

            case "higal.csv":

                //Controllo  NOMI COLONNE prima riga
                tableNamesRight = new String[]{"ID", "GLON", "GLAT", "TEMP", "L/M", "SURF_DENS", "EVOL_FLAG"};
                if (!(FirstLineOK(allLines.get(0), tableNamesRight))) {
                    System.out.println("E' stato inserito un csv errato"); //STAMPARE ROBE A SCHERMO html
                } else {

                    try {
                        for (int i = 1; i < allLines.size(); i++) {

                            String[] line = allLines.get(i);
                            String query;

                            try {

                                Double mass = Double.parseDouble(line[3]) / Double.parseDouble(line[4]);
                                query = "INSERT INTO Clumps( ClumpID, GalacticLongitude, GalacticLatitude, Temperature, LMRatio, Mass, Surfecedensity , Type, higalmaps)" +
                                        " VALUES(" + Integer.parseInt(line[0]) + ", " + Double.parseDouble(line[1]) + ", " + Double.parseDouble(line[2]) + ", " +
                                        Double.parseDouble(line[3]) + ", " + Double.parseDouble(line[4]) + ", " + Double.parseDouble(line[5])
                                        + ", " + mass + ", " + Integer.parseInt(line[6]) + ",'HiGal');";

                            } catch (NumberFormatException e) {
                                //Controllo type sulle singole righe
                                System.out.println("il formato del csv non è adatto a questa operazione"); //STAMPARE ROBE A SCHERMO html
                                return false;

                            }
                            Statement statement = connection.createStatement();
                            statement.executeUpdate(query);

                        }
                        connection.commit();
                        disconnect(connection);

                    } catch (SQLException e) {
                        e.printStackTrace();
                        try {
                            connection.rollback();
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
                return true;


            case "higal_additionalinfo.csv":

                List<Integer> existingClump= GetOldClump(connection);
                List<Integer> newClump= new ArrayList<>();

                //Controllo  NOMI COLONNE prima riga
                tableNamesRight = new String[]{"SOURCE_ID", "S70", "S160", "S250", "S350", "S500", "FW70_1", "FW70_2", "FW160_1", "FW160_2", "FW250_1",
                        "FW250_2", "FW350_1", "FW350_2", "FW500_1", "FW500_2", "PA70", "PA160", "PA250", "PA350", "PA500"};

                if (!(FirstLineOK(allLines.get(0), tableNamesRight))) {
                    System.out.println("E' stato inserito un csv errato");//STAMPARE ROBE A SCHERMO html
                } else {

                    for (int i = 1; i < allLines.size(); i++) {

                        String[] line = allLines.get(i);
                        if (!(existingClump.contains(Integer.parseInt(line[0])))&& !(newClump.contains(Integer.parseInt(line[0])))) {
                            newClump.add(Integer.parseInt(line[0]));
                        }
                    }


                    try {//Aggiornamento tabella Clumps
                        for (int j=0; j<newClump.size();j++){
                            String queryNewClumps = "INSERT INTO Clumps (clumpid) Values (" + newClump.get(j) + ");";
                            Statement statement = connection.createStatement();

                            statement.executeUpdate(queryNewClumps);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }


                    try {
                        for (int i = 1; i < allLines.size(); i++) {

                            String[] line = allLines.get(i);

                            List<String> queriesEllipses = new ArrayList<>();
                            List<String> queriesFluxes = new ArrayList<>();


                            try {

                                String query; //Riempimento tabella Ellipses
                                String query2; //Riempimento tabella Fluxes

                                if (Double.parseDouble(line[1]) != 0.0) { //BANDA 70

                                    query = "INSERT INTO Ellipses (Clump, Band, Maxaxis, Minaxis, PositionAngle) VALUES (" +
                                            Integer.parseInt(line[0]) + ", " + Double.parseDouble("70.0") + ", " + Double.parseDouble(line[6]) + ", " +
                                            Double.parseDouble(line[7]) + ", " + Double.parseDouble(line[16]) + ");";
                                    queriesEllipses.add(query);

                                    query2 = "INSERT INTO fluxes(Value, Band, Clump) VALUES(" + Double.parseDouble(line[1]) + ", " +
                                            Double.parseDouble("70.0") + ", " + Integer.parseInt(line[0]) + ");";
                                    queriesFluxes.add(query2);

                                }
                                if (Double.parseDouble(line[2]) != 0.0) { //BANDA 160
                                    query = "INSERT INTO Ellipses (Clump, Band, Maxaxis, Minaxis, PositionAngle) VALUES (" +
                                            Integer.parseInt(line[0]) + ", " + Double.parseDouble("160.0") + ", " + Double.parseDouble(line[8]) + ", " +
                                            Double.parseDouble(line[9]) + ", " + Double.parseDouble(line[17]) + ");";
                                    queriesEllipses.add(query);

                                    query2 = "INSERT INTO fluxes( Value, Band, Clump) VALUES(" + Double.parseDouble(line[2]) + ", " +
                                            Double.parseDouble("160.0") + ", " + Integer.parseInt(line[0]) + ");";
                                    queriesFluxes.add(query2);
                                }
                                if (Double.parseDouble(line[3]) != 0.0) { //BANDA 250
                                    query = "INSERT INTO Ellipses (Clump, Band, Maxaxis, Minaxis, PositionAngle) VALUES (" +
                                            Integer.parseInt(line[0]) + ", " + Double.parseDouble("250.0") + ", " + Double.parseDouble(line[10]) + ", " +
                                            Double.parseDouble(line[11]) + ", " + Double.parseDouble(line[18]) + ");";
                                    queriesEllipses.add(query);

                                    query2 = "INSERT INTO fluxes(Value, Band, Clump) VALUES(" + Double.parseDouble(line[3]) + ", " +
                                            Double.parseDouble("250.0") + ", " + Integer.parseInt(line[0]) + ");";
                                    queriesFluxes.add(query2);
                                }
                                if (Double.parseDouble(line[4]) != 0.0) { //BANDA 350
                                    query = "INSERT INTO Ellipses (Clump, Band, Maxaxis, Minaxis, PositionAngle) VALUES (" +
                                            Integer.parseInt(line[0]) + ", " + Double.parseDouble("350.0") + ", " + Double.parseDouble(line[12]) + ", " +
                                            Double.parseDouble(line[13]) + ", " + Double.parseDouble(line[19]) + ");";
                                    queriesEllipses.add(query);

                                    query2 = "INSERT INTO fluxes(Value, Band, Clump) VALUES(" + Double.parseDouble(line[4]) + ", " +
                                            Double.parseDouble("350.0") + ", " + Integer.parseInt(line[0]) + ");";
                                    queriesFluxes.add(query2);
                                }
                                if (Double.parseDouble(line[5]) != 0.0) { //BANDA 500
                                    query = "INSERT INTO Ellipses (Clump, Band, Maxaxis, Minaxis, PositionAngle) VALUES (" +
                                            Integer.parseInt(line[0]) + ", " + Double.parseDouble("500.0") + ", " + Double.parseDouble(line[14]) + ", " +
                                            Double.parseDouble(line[15]) + ", " + Double.parseDouble(line[20]) + ");";
                                    queriesEllipses.add(query);

                                    query2 = "INSERT INTO fluxes(Value, Band, Clump) VALUES(" + Double.parseDouble(line[5]) + ", " +
                                            Double.parseDouble("500.0") + ", " + Integer.parseInt(line[0]) + ");";
                                    queriesFluxes.add(query2);
                                }

                                //TEST:  System.out.println(Arrays.toString(new List[]{queriesEllipses}));

                            } catch (NumberFormatException e) {
                                //Controllo type sulle singole righe
                                System.out.println("il formato del csv non è adatto a questa operazione"); //STAMPARE ROBE A SCHERMO html
                                return false;

                            }
                            Statement statement = connection.createStatement();

                            for (String s : queriesEllipses) statement.executeUpdate(s);

                            for (String s : queriesFluxes) statement.executeUpdate(s);

                        }
                        connection.commit();
                        disconnect(connection);

                    } catch (SQLException e) {
                        e.printStackTrace();
                        try {
                            connection.rollback();
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
                return true;

            case "r08.csv":

                tableNamesRight = new String[]{"SSTGLMC", "GLon", "GLat" ,"[3.6]G", "[4.5]G", "[5.8]G", "[8.0]G"};

                if (!(FirstLineOK(allLines.get(0), tableNamesRight))) {
                    System.out.println("E' stato inserito un csv errato"); //STAMPARE ROBE A SCHERMO html
                } else {

                    try {
                        for (int i = 1; i < allLines.size(); i++) {

                            String[] line = allLines.get(i);
                            String query;
                            String query2;
                            String query3;
                            List<String> queriesFluxes = new ArrayList<>();

                            try {

                                //Riempimento tabella SourcesGLIMPSE
                                query = "INSERT INTO sources (sourceid, galacticLongitude, galacticLatitude) VALUES ('" +
                                        line[0]+ "', " + Double.parseDouble(line[1])+ ", " + Double.parseDouble(line[2]) +");";

                                if (!(line[3].equals("     "))) {
                                    query2 = "INSERT INTO fluxes (value, band, source) VALUES (" +
                                            Double.parseDouble(line[3])+ ", " +Double.parseDouble("3.6")+ ", '" + line[0] +"');";
                                    queriesFluxes.add(query2);
                                }

                                if (!(line[4].equals("     "))) {
                                    query2 = "INSERT INTO fluxes (value, band, source) VALUES (" +
                                            Double.parseDouble(line[4])+ ", " +Double.parseDouble("4.5")+ ", '" + line[0] +"');";
                                    queriesFluxes.add(query2);
                                }

                                if (!(line[5].equals("     ")))  {
                                    query2 = "INSERT INTO fluxes (value, band, source) VALUES (" +
                                            Double.parseDouble(line[5])+ ", " +Double.parseDouble("5.8")+ ", '" + line[0] +"');";
                                    queriesFluxes.add(query2);
                                }

                                if ((line[6].equals("     ")))  {
                                    query2 = "INSERT INTO fluxes (value, band, source) VALUES (" +
                                            Double.parseDouble(line[6])+ ", " +Double.parseDouble("8.0")+ ", '" + line[0] +"');";
                                    queriesFluxes.add(query2);
                                }

                                query3= "INSERT INTO collection (starmap, source) VALUES ('Glimpse', '"  +line[0]+"');";
                            } catch (NumberFormatException e) {
                                //Controllo type sulle singole righe
                                System.out.println("il formato del csv non è adatto a questa operazione"); //STAMPARE ROBE A SCHERMO html
                                return false;

                            }
                            Statement statement = connection.createStatement();
                            statement.executeUpdate(query);
                            for (String s : queriesFluxes) statement.executeUpdate(s);

                            statement.executeUpdate(query3);

                        }
                        connection.commit();
                        disconnect(connection);

                    } catch (SQLException e) {
                        e.printStackTrace();
                        try {
                            connection.rollback();
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
                return true;


            case "mips.csv"://MANCANO ALCUNE CHIAVI
                List<String> existingSources= GetOldSources(connection);
                List<String> newSources= new ArrayList<>();

                //Controllo  NOMI COLONNE prima rmiga
                tableNamesRight = new String[]{"MIPSGAL", "GLON", "GLAT", "[24]", "e_[24]", "GLIMPSE"};

                if (!(FirstLineOK(allLines.get(0), tableNamesRight))) {
                    System.out.println("E' stato inserito un csv errato"); //STAMPARE ROBE A SCHERMO html
                } else {


                    for (int i = 1; i < allLines.size(); i++) {

                        String[] line = allLines.get(i); //OUT OF 5e

                        if (line.length>5) {
                            if (!(existingSources.contains(line[5])) && !(newSources.contains(line[5]))) {
                                newSources.add(line[5]);
                            }
                        }
                    }


                    try {//Aggiornamento tabella Clumps
                        for (int j=0; j<newSources.size();j++){
                            String queryNewSources = "INSERT INTO sources (sourceid) Values ('" + newSources.get(j) + "');";
                            Statement statement = connection.createStatement();
                            statement.executeUpdate(queryNewSources);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }


                    try {
                        for (int i = 1; i < allLines.size(); i++) {

                            String[] line = allLines.get(i);
                            String query;
                            String query2;
                            String query3;


                            try {
                                if (line.length==6) {

                                //Riempimento tabella Sources
                                    query = "INSERT INTO sources (sourceid, galacticLongitude, galacticLatitude, comparedSource) VALUES ('" +
                                            line[0]+ "', " + Double.parseDouble(line[1])+ ", " + Double.parseDouble(line[2]) + ", '" + line[5]+"');";
                                }else{
                                    query = "INSERT INTO sources (sourceid, galacticLongitude, galacticLatitude) VALUES ('" +
                                            line[0]+ "', " + Double.parseDouble(line[1])+ ", " + Double.parseDouble(line[2])+");";

                                }
                                //clRiempimento tabella fluxes
                                query2 = "INSERT INTO fluxes (value, error, band, source) VALUES ("+
                                        Double.parseDouble(line[3])+ ", " +  Double.parseDouble(line[4])+ ", " + Double.parseDouble("24.0")+ ", '"  + line[0]+"');";

                                query3= "INSERT INTO collection (starmap, source) VALUES ('MIPS-GAL', '"  +line[0]+"');";

                            } catch (NumberFormatException e) {
                                //Controllo type sulle singole righe
                                System.out.println("il formato del csv non è adatto a questa operazione"); //STAMPARE ROBE A SCHERMO html
                                return false;

                            }
                            Statement statement = connection.createStatement();
                            statement.executeUpdate(query);
                            statement.executeUpdate(query2);
                            statement.executeUpdate(query3);

                        }
                        connection.commit();
                        disconnect(connection);

                    } catch (SQLException e) {
                        e.printStackTrace();
                        try {
                            connection.rollback();
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
                return true;
        }
        return false;
    }


    private boolean FirstLineOK(String[] csvFirstLine, String[] expetedLine) {
        for (int j = 0; j < csvFirstLine.length; j++) {
            if (!(csvFirstLine[j].equals(expetedLine[j]))) {
                return false;
            }
        }
        return true;
    }



    private List<Integer> GetOldClump(Connection connection) {

        List<Integer> existingClump= new ArrayList<>();

        try {

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT clumpid FROM Clumps;");

            while (rs.next())
                existingClump.add(rs.getInt("clumpid"));
            // TEST: System.out.println(Arrays.toString(existingClump.toArray()));

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            }catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return existingClump;
    }

    private List<String> GetOldSources(Connection connection) {

        List<String> existingSources= new ArrayList<>();

        try {

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT sourceid FROM Sources;");

            while (rs.next())
                existingSources.add(rs.getString("sourceid"));
            // TEST: System.out.println(Arrays.toString(existingClump.toArray()));

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            }catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return existingSources;
    }


}

