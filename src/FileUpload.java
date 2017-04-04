import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.*;
import java.nio.file.*;
import java.sql.*;
import java.util.List;


public class FileUpload extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletFileUpload sf = new ServletFileUpload(new DiskFileItemFactory());

        String savePath = request.getServletContext().getRealPath("");
        //System.out.print(savePath); =*/OAV/out/artifacts/OAV_war_exploded/

        try {
            List<FileItem> multifiles = sf.parseRequest(request);

            for (FileItem item : multifiles) {

                String fileName = item.getName();
                item.write(new File(savePath + fileName));

                getSplitted(savePath, fileName);

            }
        } catch (FileUploadException | ClassNotFoundException | SQLException e ) {
            e.printStackTrace();
        } catch (Exception b) {
            b.printStackTrace();
        }
    }



    private void getSplitted(String path, String fileName) throws SQLException, ClassNotFoundException {

        try{
            List<String> lines = Files.readAllLines(Paths.get(path+fileName));
            for (String line : lines){
                line = line.replace("\"", "");
                String [] result = line.split(",");
                //System .out.print(Arrays.toString(result)); [ID, GLON, GLAT, TEMP, L/M, SURF_DENS, EVOL_FLAG]

               /* lo esegue una volta sola :'(
               switch (fileName){
                    case "higal.csv": ConnectionH(result);
                    case "higal_additionalinfo.csv": ConnectionHaI(result);
                    case "mips.csv": ConnectionM(result);
                    case "r08.csv": ConnectionR(result);
                }*/

               if (fileName.equals("higal.csv")); ConnectionH(result);
                /*if (fileName.equals("higal_additionalinfo.csv")); ConnectionHaI(result);
               if (fileName.equals("mips.csv")); ConnectionM(result);
               if (fileName.equals("r08.csv")); ConnectionR(result);*/

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //return result;
    }




    private void ConnectionH(String[] result) throws SQLException, ClassNotFoundException{
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection( "jdbc:postgresql://localhost:5432/postgres","postgres","root");

        PreparedStatement statement = con.prepareStatement("INSERT INTO clumps(id_clump, longitudineGalattica, latitudineGalattica, temperatura, rappLM,densitaSuperficiale, tipo) VALUES(?,?,?,?,?,?,?)");
        statement.setInt(1, Integer.parseInt(result[0]));
        statement.setDouble(2, Double.parseDouble(result[1]));
        statement.setDouble(3, Double.parseDouble(result[2]));
        statement.setDouble(4, Double.parseDouble(result[3]));
        statement.setDouble(5, Double.parseDouble(result[4]));
        statement.setDouble(6, Double.parseDouble(result[5]));
        statement.setInt(7, Integer.parseInt(result[6]));
        statement.executeUpdate();
        statement.close();
        con.close();
    }


    /*????????????????????'
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


