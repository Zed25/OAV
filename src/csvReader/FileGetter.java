package csvReader;


/**
 * Created by dilettalagom on 16/04/17.
 */
public class FileGetter {


    private static FileGetter fileControllerInstance = null;
    public static synchronized FileGetter getFileControllerInstance() {
        if(fileControllerInstance == null)
            fileControllerInstance = new FileGetter();
        return fileControllerInstance;
    }

    public String filescelto;

}
