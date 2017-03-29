package Model;
import DAO.DB;

/**
 * Created by andrea on 29/03/17.
 */
public class Map implements DB{

    private String name;

    public String getName() {
        return name;
    }

    public Map() {    }

    public Map(String name) {
        this.name = name;
    }


    public void searchObject(String mapName, int band){

    }
}
