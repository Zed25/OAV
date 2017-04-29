package beans.login;

import java.util.List;

/**
 * Created by simone on 03/04/17.
 */
public class AgencyBean {
    String name;

    /**
     * Agency bean's constructor
     */
    public AgencyBean(){
        name = "";
    }

    /**GETTER AND SETTER**/
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
