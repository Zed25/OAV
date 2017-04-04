package beans.login;

import Controllers.SearchController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by simone on 03/04/17.
 */
public class AgenciesListBean {
    List<AgencyBean> agencyBeansList;

    public AgenciesListBean(){
        agencyBeansList = null;
    }

    public void getAllAgencies(){
        List<AgencyBean> agencyBeanList = SearchController.getInstance().getAllAgencies();
        this.setAgencyBeansList(agencyBeanList);
    }

    public List<AgencyBean> getAgencyBeansList() {
        return agencyBeansList;
    }

    public void setAgencyBeansList(List<AgencyBean> agencyBeansList) {
        this.agencyBeansList = agencyBeansList;
    }
}
