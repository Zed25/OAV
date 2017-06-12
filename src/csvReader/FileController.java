package csvReader;


/**
 * Created by dilettalagom on 16/04/17.
 */
public class FileController {


    private static FileController fileControllerInstance = null;
    public static synchronized FileController getFileControllerInstance() {
        if(fileControllerInstance == null)
            fileControllerInstance = new FileController();
        return fileControllerInstance;
    }
    public String filescelto;

    public boolean notEmpty;

}
