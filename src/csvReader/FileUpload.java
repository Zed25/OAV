package csvReader;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.*;
import java.nio.file.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;




public class FileUpload extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /*String choosenFile = request.getParameter("filescelto");
        System.out.print("Radiobutton " + choosenFile);*/

        ServletFileUpload sf = new ServletFileUpload(new DiskFileItemFactory());
        String savePath = request.getServletContext().getRealPath("");         //System.out.print(savePath); =*/OAV/out/artifacts/OAV_war_exploded/

        try {
            List<FileItem> multifiles = sf.parseRequest(request);

            for (FileItem item : multifiles) {

                String fileName = item.getName();
                item.write(new File(savePath + fileName));

                if (getSplitted(savePath, fileName)) {
                    response.setContentType("errore");
                }
            }
        } catch (Exception b) {
            b.printStackTrace();
        }
    }



    private boolean getSplitted(String path, String fileName) throws SQLException, ClassNotFoundException {

        List<String[]> allLines = null;
        try{
            List<String> lines = Files.readAllLines(Paths.get(path+fileName));
            allLines= new ArrayList<>();
            for (String line : lines){

                line = line.replace("\"", "");
                String [] result = line.split(",");
                if (!(result.length<3)) {
                    allLines.add(result); //restituisce un csv che parte dalla riga con i NOMI DELLE TABELLE
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (allLines!= null){
            FileDAO fileDAO = new FileDAO();
            if (fileDAO.fillingTable(fileName, allLines))
                return true;
        }

        return false;


    }




    /*????????????????????'

    -controllo sui nomi delle colonne che rispettino i nomi delle colonne delle tabelle
    -controllo su una result[] tipo per i type
    -radiobotton
    -R3.4
    -update dei csv
    -quali sono tabelle dei csv

    private void ConnectionHaI(String[] result) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection( "jdbc:postgresql://localhost:5432/postgres","postgres","root");

        PreparedStatement statement = con.prepareStatement("INSERT INTO ..... ( ) VALUES ()");

        statement.executeUpdate();
        statement.close();
        con.close();
    }


    private void ConnectionM(String[] result)throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection( "jdbc:postgresql://localhost:5432/postgres","postgres","root");

        PreparedStatement statement = con.prepareStatement("INSERT INTO __?_ (id, longitudine, latitudine, fluxMagn, fluxErr, id2) VALUES (?,?,?,?,?,?)");
        statement.setString(1, (result[0]));
        statement.setDouble(2, Double.parseDouble(result[1]));
        statement.setDouble(3, Double.parseDouble(result[2]));
        statement.setDouble(4, Double.parseDouble(result[3]));
        statement.setDouble(5, Double.parseDouble(result[4]));
        statement.setString(6, (result[5]));

        statement.executeUpdate();
        statement.close();
        con.close();
    }

    private void ConnectionR(String[] result) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection( "jdbc:postgresql://localhost:5432/postgres","postgres","root");

        PreparedStatement statement = con.prepareStatement("INSERT INTO _?_ (id, longitudine,latitudine, flux1, flux2, flux3, flux4) VALUES (?,?,?,?,?)");
        statement.setString(1, (result[0]));
        statement.setDouble(2, Double.parseDouble(result[1]));
        statement.setDouble(3, Double.parseDouble(result[2]));
        statement.setDouble(4, Double.parseDouble(result[3]));
        statement.setDouble(5, Double.parseDouble(result[4]));
        statement.executeUpdate();
        statement.close();
        con.close();

    }*/


}


