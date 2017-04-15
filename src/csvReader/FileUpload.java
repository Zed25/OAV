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

        ServletFileUpload sf = new ServletFileUpload(new DiskFileItemFactory());
        String savePath = request.getServletContext().getRealPath("");         //System.out.print(savePath); =*/OAV/out/artifacts/OAV_war_exploded/

        String filecheck = request.getParameter("filescelto");
      //  System.out.println("fileupload" + filecheck);

        try {

           List<FileItem> multifiles = sf.parseRequest(request);

            for (FileItem item : multifiles) {

                String fileName = item.getName();
              //  if (filecheck.equals(fileName)){
                    item.write(new File(savePath + fileName));

                    if (getSplitted(savePath, fileName)) {
                        response.setContentType("errore");
                    }
              /*  }else{
                    System.out.println("Il file inserito non corrisponde a quello selezionato");
                }*/
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




    /*TODO:
        -R3.4
     */

}


