package csvReader;

import Controllers.FileController;
import DAO.FileDAO;
import enumerations.ErrorType;
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

    public String filescelto;
    FileController c = FileController.getFileControllerInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        filescelto = c.filescelto;

        ServletFileUpload sf = new ServletFileUpload(new DiskFileItemFactory());
        String savePath = request.getServletContext().getRealPath("");         //System.out.print(savePath); =*/OAV/out/artifacts/OAV_war_exploded/


            try {
                List<FileItem> multifiles = sf.parseRequest(request);

                //System.out.println(Arrays.toString(new List[]{multifiles}));

                FileItem item = multifiles.get(0);
                String fileName = item.getName();
                if (fileName.equals(filescelto)) {
                    item.write(new File(savePath + fileName));

                    ErrorType error = getSplitted(savePath, fileName);

                    //System.out.println("errore durante la lettura del file");
                    c.errorToShow = error;

                }
               /* }else{
                    //System.out.println("Hai inserito il file "+fileName+", ma hai selezionato il file "+filescelto+". Riprova");
                    c.errorToShow=ErrorType.DIFFERENTCHOOSEFILE;
                }*/

            } catch (Exception b) {
                b.printStackTrace();

            }
        request.getRequestDispatcher("/resultUpload.jsp").forward(request, response);

        }


    private ErrorType getSplitted(String path, String fileName) throws SQLException, ClassNotFoundException {

        ErrorType error = ErrorType.NO_ERR;
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

        if (allLines!= null) {
            FileDAO fileDAO = new FileDAO();
            error = fileDAO.fillingTable(fileName, allLines);

            System.out.println(error.toString());
        }
        return error;
    }
}


