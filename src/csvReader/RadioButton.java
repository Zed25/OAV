package csvReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dilettalagom on 05/04/17.
 */
public class RadioButton extends HttpServlet {

    public String choosenFile;

    public String check(){
        return choosenFile;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        choosenFile = request.getParameter("filescelto");
        System.out.print("Radiobutton " + choosenFile);
    }
}
