package beans.login;

import Controllers.SearchController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by simone on 03/04/17.
 */
public class AgenciesListBean {
    private List<AgencyBean> agencyBeansList;

    /**
     * agencies bean's list constructor.
     * Initially the list is set to null, it means that the bean is empty or not initialize
     */
    public AgenciesListBean(){
        agencyBeansList = null;
    }


    /**
     * call the search controller and get all agencies in the db.
     * It sets the bean list attribute to "all agencies"
     */
    public void getAllAgencies(){
        List<AgencyBean> agencyBeanList = SearchController.getInstance().getAllAgencies();
        this.setAgencyBeansList(agencyBeanList);
    }

    /**GETTER AND SETTER**/
    public List<AgencyBean> getAgencyBeansList() {
        return agencyBeansList;
    }

    public void setAgencyBeansList(List<AgencyBean> agencyBeansList) {
        this.agencyBeansList = agencyBeansList;
    }
}
