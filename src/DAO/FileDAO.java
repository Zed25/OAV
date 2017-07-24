package DAO;

import Controllers.FileController;
import DAO.SuperDAO;
import enumerations.ConnectionType;
import enumerations.ErrorType;
import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dilettalagom on 07/04/17.
 */


public class FileDAO extends SuperDAO {


    public ErrorType fillingTable(String fileName, List<String[]> allLines) {

        ErrorType error = ErrorType.NO_ERR;

        Connection connection = connect(ConnectionType.COMPQUERY);
        String[] tableNamesRight;

        FileController controller = FileController.getFileControllerInstance();

        switch (fileName) {

            case "higal.csv":

                //Controllo  NOMI COLONNE prima riga
                tableNamesRight = new String[]{"ID", "GLON", "GLAT", "TEMP", "L/M", "SURF_DENS", "EVOL_FLAG"};
                if (!(FirstLineOK(allLines.get(0), tableNamesRight))) {
                    error=ErrorType.DIFFERENTTABLEFILE;
                } else {

                    try {
                        List<Integer> existingClump = GetOldClump(connection);
                        for (int i = 1; i < allLines.size(); i++) {

                            String[] line = allLines.get(i);


                            Clump clump = controller.createClump(Integer.parseInt(line[0]),Double.parseDouble(line[1]), Double.parseDouble(line[2]),
                                    Float.parseFloat(line[3]),Float.parseFloat(line[4]),Float.parseFloat(line[5]),Integer.parseInt(line[6]));


                            String query;
                            try {
                                if (existingClump.contains(clump.getClumpID())){
                                    query= "UPDATE clumps " +
                                            "SET galacticlatitude = " + clump.getGalLat()+", " +
                                            "galacticlongitude = "+clump.getGalLong()+", " +
                                            "temperature = "+ clump.getTemperature()+", " +
                                            "lmratio = "+ clump.getLmRatio()+", " +
                                            "surfacedensity = "+clump.getDensity()+", " +
                                            "type ="+clump.getType()+" " +
                                            "WHERE (clumpid = "+clump.getClumpID()+");";
                                }else {


                                    query = "INSERT INTO Clumps( ClumpID, GalacticLongitude, GalacticLatitude, Temperature, LMRatio, surfacedensity , Type, higalmaps)" +
                                            " VALUES(" + clump.getClumpID() + ", " + clump.getGalLong() + ", " + clump.getGalLat() + ", " +
                                            clump.getTemperature() + ", " + clump.getLmRatio() + ", " + clump.getDensity() + ", " + clump.getType() + ",'HiGal');";
                                }


                            } catch (NumberFormatException e) {
                                //Controllo type sulle singole righe
                                //System.out.println("il formato del csv non è adatto a questa operazione");
                                error=ErrorType.UNFORMATFILE;
                                return error;

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
                return error;


            case "higal_additionalinfo.csv":

                List<Integer> existingClump = GetOldClump(connection);
                List<Integer> newClump = new ArrayList<>();

                //Controllo  NOMI COLONNE prima riga
                tableNamesRight = new String[]{"SOURCE_ID", "S70", "S160", "S250", "S350", "S500", "FW70_1", "FW70_2", "FW160_1", "FW160_2", "FW250_1",
                        "FW250_2", "FW350_1", "FW350_2", "FW500_1", "FW500_2", "PA70", "PA160", "PA250", "PA350", "PA500"};

                if (!(FirstLineOK(allLines.get(0), tableNamesRight))) {
                    error=ErrorType.DIFFERENTTABLEFILE;
                } else {

                    for (int i = 1; i < allLines.size(); i++) {

                        String[] line = allLines.get(i);
                        if (!(existingClump.contains(Integer.parseInt(line[0]))) && !(newClump.contains(Integer.parseInt(line[0])))) {
                            newClump.add(Integer.parseInt(line[0]));
                        }
                    }

                    try {//Aggiornamento tabella Clumps
                        for (int j = 0; j < newClump.size(); j++) {
                            String queryNewClumps = "INSERT INTO Clumps (clumpid, higalmaps) Values (" + newClump.get(j) + ",'HiGal');";
                            Statement statement = connection.createStatement();

                            statement.executeUpdate(queryNewClumps);
                        }
                    }catch (SQLException e) {
                        e.printStackTrace();
                    }

                    try {
                        for (int i = 1; i < allLines.size(); i++) {

                            String[] line = allLines.get(i);

                            List<String> queriesEllipses = new ArrayList<>();
                            List<String> queriesFluxes = new ArrayList<>();


                            try {

                                String query; //Riempimento tabella Ellipse
                                String query2; //Riempimento tabella Fluxes


                                if (Double.parseDouble(line[1]) != 0.0) { //BANDA 70
                                    Band band = controller.createBand(Double.parseDouble("70.0"));

                                    Ellipse ellipse = controller.createEllipse(Integer.parseInt(line[0]), band, Double.parseDouble(line[6]),
                                            Double.parseDouble(line[7]), Double.parseDouble(line[16]));

                                    query = "INSERT INTO Ellipses (Clump, Band, Maxaxis, Minaxis, PositionAngle) " +
                                            "VALUES ('" + ellipse.getClump() + "', '" + ellipse.getBand().getResolution() + "', '" + ellipse.getMaxaxis() + "', '" +
                                            ellipse.getMinaxis() + "', '" + ellipse.getPositionangle() + "');";
                                    queriesEllipses.add(query);


                                    Flux flux = controller.createFlux(Float.parseFloat(line[1]), Float.parseFloat("0"), band);

                                    query2 = "INSERT INTO fluxes(Value, Band, Clump) VALUES('" + flux.getValue() + "', '" +
                                            flux.getBand().getResolution() + "', '" + ellipse.getClump() + "');";
                                    queriesFluxes.add(query2);
                                }


                                if (Double.parseDouble(line[2]) != 0.0) { //BANDA 160
                                    Band band = controller.createBand(Double.parseDouble("160.0"));

                                    Ellipse ellipse = controller.createEllipse(Integer.parseInt(line[0]), band, Double.parseDouble(line[8]),
                                            Double.parseDouble(line[9]), Double.parseDouble(line[17]));

                                    query = "INSERT INTO Ellipses (Clump, Band, Maxaxis, Minaxis, PositionAngle) " +
                                            "VALUES ('" + ellipse.getClump() + "', '" + ellipse.getBand().getResolution() + "', '" + ellipse.getMaxaxis() + "', '" +
                                            ellipse.getMinaxis() + "', '" + ellipse.getPositionangle() + "');";
                                    queriesEllipses.add(query);

                                    Flux flux = controller.createFlux(Float.parseFloat(line[2]), Float.parseFloat("0"), band);

                                    query2 = "INSERT INTO fluxes(Value, Band, Clump) VALUES('" + flux.getValue() + "', '" +
                                            flux.getBand().getResolution() + "', '" + ellipse.getClump() + "');";
                                    queriesFluxes.add(query2);
                                }

                                if (Double.parseDouble(line[3]) != 0.0) { //BANDA 250
                                    Band band = controller.createBand(Double.parseDouble("250.0"));

                                    Ellipse ellipse = controller.createEllipse(Integer.parseInt(line[0]), band, Double.parseDouble(line[10]),
                                            Double.parseDouble(line[11]), Double.parseDouble(line[18]));

                                    query = "INSERT INTO Ellipses (Clump, Band, Maxaxis, Minaxis, PositionAngle) " +
                                            "VALUES ('" + ellipse.getClump() + "', '" + ellipse.getBand().getResolution() + "', '" + ellipse.getMaxaxis() + "', '" +
                                            ellipse.getMinaxis() + "', '" + ellipse.getPositionangle() + "');";
                                    queriesEllipses.add(query);

                                    Flux flux = controller.createFlux(Float.parseFloat(line[3]), Float.parseFloat("0"), band);
                                    query2 = "INSERT INTO fluxes(Value, Band, Clump) VALUES('" + flux.getValue() + "', '" +
                                            flux.getBand().getResolution() + "', '" + ellipse.getClump() + "');";

                                    queriesFluxes.add(query2);
                                }

                                if (Double.parseDouble(line[4]) != 0.0) { //BANDA 350
                                    Band band = controller.createBand(Double.parseDouble("350.0"));

                                    Ellipse ellipse = controller.createEllipse(Integer.parseInt(line[0]), band, Double.parseDouble(line[12]),
                                            Double.parseDouble(line[13]), Double.parseDouble(line[19]));

                                    query = "INSERT INTO Ellipses (Clump, Band, Maxaxis, Minaxis, PositionAngle) " +
                                            "VALUES ('" + ellipse.getClump() + "', '" + ellipse.getBand().getResolution() + "', '" + ellipse.getMaxaxis() + "', '" +
                                            ellipse.getMinaxis() + "', '" + ellipse.getPositionangle() + "');";

                                    queriesEllipses.add(query);

                                    Flux flux = controller.createFlux(Float.parseFloat(line[4]), Float.parseFloat("0"), band);

                                    query2 = "INSERT INTO fluxes(Value, Band, Clump) VALUES('" + flux.getValue() + "', '" +
                                            flux.getBand().getResolution() + "', '" + ellipse.getClump() + "');";

                                    queriesFluxes.add(query2);
                                }

                                if (Double.parseDouble(line[5]) != 0.0) { //BANDA 500
                                    Band band = controller.createBand(Double.parseDouble("500.0"));

                                    Ellipse ellipse = controller.createEllipse(Integer.parseInt(line[0]), band, Double.parseDouble(line[14]),
                                            Double.parseDouble(line[15]), Double.parseDouble(line[20]));

                                    query = "INSERT INTO Ellipses (Clump, Band, Maxaxis, Minaxis, PositionAngle) " +
                                            "VALUES ('" + ellipse.getClump() + "', '" + ellipse.getBand().getResolution() + "', '" + ellipse.getMaxaxis() + "', '" +
                                            ellipse.getMinaxis() + "', '" + ellipse.getPositionangle() + "');";
                                    queriesEllipses.add(query);

                                    Flux flux = controller.createFlux(Float.parseFloat(line[5]), Float.parseFloat("0"), band);

                                    query2 = "INSERT INTO fluxes(Value, Band, Clump) VALUES('" + flux.getValue() + "', '" +
                                            flux.getBand().getResolution() + "', '" + ellipse.getClump() + "');";

                                    queriesFluxes.add(query2);
                                }

                                //TEST:  System.out.println(Arrays.toString(new List[]{queriesEllipses}));

                            } catch (NumberFormatException e) {
                                //Controllo type sulle singole righe
                                //System.out.println("il formato del csv non è adatto a questa operazione" + error);
                                error=ErrorType.UNFORMATFILE;
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

                return error;

            case "r08.csv":

                tableNamesRight = new String[]{"SSTGLMC", "GLon", "GLat", "[3.6]G", "[4.5]G", "[5.8]G", "[8.0]G"};

                if (!(FirstLineOK(allLines.get(0), tableNamesRight))) {
                    error=ErrorType.DIFFERENTTABLEFILE;
                } else {

                    try {
                        for (int i = 1; i < allLines.size(); i++) {

                            String[] line = allLines.get(i);
                            String query;
                            String query2;
                            String query3;
                            List<String> queriesFluxes = new ArrayList<>();
                            List<String> existingSources = GetOldSources(connection);


                            try {

                                Source source = controller.createSource(line[0],Double.parseDouble(line[1]),Double.parseDouble(line[2]), "");

                                //Riempimento tabella SourcesGLIMPSE
                                if (existingSources.contains(source.getSourceID())) {

                                    query = "UPDATE sources " +
                                            "SET  galacticlongitude = "+ source.getGalLong()+ ",  galacticlatitude= " + source.getGalLat()+
                                            " WHERE (sourceid ='"+ source.getSourceID()+"');";
                                }else {

                                    query = "INSERT INTO sources (sourceid, galacticLongitude, galacticLatitude) " +
                                            "VALUES ('" + source.getSourceID() + "', " + source.getGalLong() + ", " + source.getGalLat() + ");";

                                }
                                //Riempimento tabelle flusso
                                if (!(line[3].equals("     "))) {

                                    Band band = controller.createBand(Double.parseDouble("3.6"));
                                    Flux flux= controller.createFlux(Float.parseFloat(line[3]),Float.parseFloat("0"), band);
                                    query2 = "INSERT INTO fluxes (value, band, source) VALUES (" +
                                            flux.getValue() + ", " + flux.getBand().getResolution() + ", '" + source.getSourceID() + "');";
                                    queriesFluxes.add(query2);
                                }

                                if (!(line[4].equals("     "))) {

                                    Band band = controller.createBand(Double.parseDouble("4.5"));
                                    Flux flux= controller.createFlux(Float.parseFloat(line[4]),Float.parseFloat("0"), band);
                                    query2 = "INSERT INTO fluxes (value, band, source) VALUES (" +
                                            flux.getValue() + ", " + flux.getBand().getResolution() + ", '" + source.getSourceID() + "');";
                                    queriesFluxes.add(query2);
                                }

                                if (!(line[5].equals("     "))) {

                                    Band band = controller.createBand(Double.parseDouble("5.8"));
                                    Flux flux= controller.createFlux(Float.parseFloat(line[5]),Float.parseFloat("0"), band);
                                    query2 = "INSERT INTO fluxes (value, band, source) VALUES (" +
                                            flux.getValue() + ", " + flux.getBand().getResolution() + ", '" + source.getSourceID() + "');";
                                    queriesFluxes.add(query2);
                                }

                                if (!(line[6].equals("     "))) {

                                    Band band = controller.createBand(Double.parseDouble("8.0"));
                                    Flux flux= controller.createFlux(Float.parseFloat(line[6]),Float.parseFloat("0"), band);
                                    query2 = "INSERT INTO fluxes (value, band, source) VALUES (" +
                                            flux.getValue() + ", " + flux.getBand().getResolution() + ", '" + source.getSourceID() + "');";
                                    queriesFluxes.add(query2);
                                }

                                //Riempimento tabella Mappe
                                query3 = "INSERT INTO collection (starmap, source) VALUES ('Glimpse', '" + source.getSourceID() + "');";
                            } catch (NumberFormatException e) {
                                //Controllo type sulle singole righe
                                //System.out.println("il formato del csv non è adatto a questa operazione");
                                error=ErrorType.UNFORMATFILE;
                                return error;

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
                return error;


            case "mips.csv":
                List<String> existingSources = GetOldSources(connection);
                List<String> newSources = new ArrayList<>();

                //Controllo  NOMI COLONNE prima riga
                tableNamesRight = new String[]{"MIPSGAL", "GLON", "GLAT", "[24]", "e_[24]", "GLIMPSE"};

                if (!(FirstLineOK(allLines.get(0), tableNamesRight))) {
                    error=ErrorType.DIFFERENTTABLEFILE;
                } else {

                    for (int i = 1; i < allLines.size(); i++) {

                        String[] line = allLines.get(i);

                        if (line.length > 5) {
                            if (!(existingSources.contains(line[5])) && !(newSources.contains(line[5]))) {
                                newSources.add(line[5]);
                            }
                        }
                    }

                    try {//Aggiornamento tabella Sources
                        for (int j = 0; j < newSources.size(); j++) {
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
                                Source source;

                                if (line.length == 6) {
                                    //Riempimento tabella Sources
                                    source= controller.createSource(line[0],Double.parseDouble(line[1]),Double.parseDouble(line[2]),line[5]);
                                    query = "INSERT INTO sources (sourceid, galacticLongitude, galacticLatitude, comparedSource) VALUES ('" +
                                            source.getSourceID() + "', " + source.getGalLong() + ", " + source.getGalLat() + ", '" + source.getComparedSource() + "');";
                                } else {
                                    source= controller.createSource(line[0],Double.parseDouble(line[2]),Double.parseDouble(line[1]), "");
                                    query = "INSERT INTO sources (sourceid, galacticLongitude, galacticLatitude) VALUES ('" +
                                            source.getSourceID() + "', " + source.getGalLong() + ", " + source.getGalLat() + ");";

                                }
                                //Riempimento tabella fluxes
                                Band band = controller.createBand(Double.parseDouble("24.0"));
                                Flux flux = controller.createFlux(Float.parseFloat(line[3]), Float.parseFloat(line[4]), band);
                                query2 = "INSERT INTO fluxes (value, error, band, source) VALUES (" + flux.getValue() + ", " + flux.getError() +
                                        ", " + flux.getBand().getResolution() + ", '" + source.getSourceID() + "');";

                                query3 = "INSERT INTO collection (starmap, source) VALUES ('MIPS-GAL', '" + source.getSourceID() + "');";

                            } catch (NumberFormatException e) {
                                //Controllo type sulle singole righe
                                //System.out.println("il formato del csv non è adatto a questa operazione");
                                error=ErrorType.UNFORMATFILE;
                                return error;

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

                    //Riempimento s_c_membership
                    SC_membership();
                }
                return error;
        }
        return error;
    }

    private void SC_membership(){
        Connection connection = connect(ConnectionType.COMPQUERY);
        try {

            Statement statement = connection.createStatement();

           /* String query = ("INSERT INTO s_c_membership SELECT DISTINCT sources.sourceid, clumps.clumpid" +
                    "            FROM sources CROSS JOIN " +
                    "            clumps INNER JOIN ellipses ON (clumps.clumpid=ellipses.clump)" +
                    "            WHERE ( (3600* sqrt((sources.galacticlatitude - clumps.galacticlatitude)^2 +" +
                    "                            (sources.galacticlongitude - clumps.galacticlongitude)^2) <" +
                    "                            ellipses.maxaxis)" +
                    "                    AND clumps.galacticlongitude != 0 AND clumps.galacticlatitude != 0" +
                    "                    AND sources.galacticlongitude != 0 AND sources.galacticlatitude != 0);");*/

            String query = ("INSERT INTO s_c_membership SELECT DISTINCT sources.sourceid, clumps.clumpid" +
                    "            FROM sources CROSS JOIN " +
                    "            clumps INNER JOIN ellipses ON (clumps.clumpid=ellipses.clump)" +
                    "            WHERE ( (sqrt((sources.galacticlatitude - clumps.galacticlatitude)^2 +" +
                    "                            (sources.galacticlongitude - clumps.galacticlongitude)^2) <" +
                    "                            3600*ellipses.maxaxis)" +
                    "                    AND clumps.galacticlongitude != 0 AND clumps.galacticlatitude != 0" +
                    "                    AND sources.galacticlongitude != 0 AND sources.galacticlatitude != 0);");


            statement.executeUpdate(query);
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

