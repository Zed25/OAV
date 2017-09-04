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

    private FileController c = FileController.getFileControllerInstance();

    /*Checking if the file uploaded name by admin is the one actually selected in the jsp form*/
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String fileUploaded = c.fileUploaded;

        ServletFileUpload sf = new ServletFileUpload(new DiskFileItemFactory());
        String savePath = request.getServletContext().getRealPath("");

            try {
                List<FileItem> multifiles = sf.parseRequest(request);

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

    /*Parsing the csv file and sending all rows to the FileDao*/
    private ErrorType getSplitted(String path, String fileName) throws SQLException, ClassNotFoundException {

        List<String[]> allLines = null;
        try{
            List<String> lines = Files.readAllLines(Paths.get(path+fileName));
            allLines= new ArrayList<>();
            for (String line : lines){

                line = line.replace("\"", "");
                String [] result = line.split(",");
                if (!(result.length<3)) {
                    allLines.add(result); //takes the first csv's rows with the tables's names
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (allLines!= null) {
            FileDAO fileDAO = new FileDAO();
            return fileDAO.fillingTable(fileName, allLines);
        }
        return ErrorType.NO_ERR;
    }
}


