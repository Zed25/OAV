package csvReader;

import enumerations.ErrorType;
import model.Ellipse;
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        FileController c = FileController.getFileControllerInstance();
        filescelto = c.filescelto;

       //TEST: System.out.println("FileUpload " +filescelto);

        ServletFileUpload sf = new ServletFileUpload(new DiskFileItemFactory());
        String savePath = request.getServletContext().getRealPath("");         //System.out.print(savePath); =*/OAV/out/artifacts/OAV_war_exploded/

        try {
            List<FileItem> multifiles = sf.parseRequest(request);

            //System.out.println(Arrays.toString(new List[]{multifiles}));

            FileItem item = multifiles.get(0);
            String fileName = item.getName();
            if (fileName.equals(filescelto)){
                item.write(new File(savePath + fileName));

                if (!(getSplitted(savePath, fileName))) {
                    //System.out.println("errore durante la lettura del file");
                    c.errorToShow= ErrorType.READINGFILEERR;
                }

            }else{
                System.out.println("Hai inserito il file "+fileName+", ma hai selezionato il file "+filescelto+". Riprova");
                c.errorToShow=ErrorType.DIFFERENTCHOOSEFILE;
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
}


