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

    public String fileUploaded;
    FileController c = FileController.getFileControllerInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        fileUploaded = c.fileUploaded;

        ServletFileUpload sf = new ServletFileUpload(new DiskFileItemFactory());
        String savePath = request.getServletContext().getRealPath("");         //System.out.print(savePath); =*/OAV/out/artifacts/OAV_war_exploded/


            try {
                List<FileItem> multifiles = sf.parseRequest(request);

                //System.out.println(Arrays.toString(new List[]{multifiles}));

                FileItem item = multifiles.get(0);
                String fileName = item.getName();
                if (fileName.equals(fileUploaded)) {
                    item.write(new File(savePath + fileName));

                    ErrorType error = getSplitted(savePath, fileName);
                    c.errorToShow = error;

                }

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

           //TEST: ErrorType=System.out.println(error.toString());
        }
        return error;
    }
}


