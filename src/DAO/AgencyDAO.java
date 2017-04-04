package DAO;

import beans.login.AgencyBean;
import com.sun.rowset.CachedRowSetImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by simone on 03/04/17.
 */
public class AgencyDAO {

    public List<AgencyBean> getAll(){
        String query = "SELECT \"Nome\" FROM \"Agenzie\";";

        System.out.println(query); //DEBUG

        List<AgencyBean> agencyBeanList = new ArrayList<>();
        CachedRowSetImpl cachedRowSet = DataBaseManager.getInstance().dbQuery(query);

        if(cachedRowSet != null){
            try {
                while (cachedRowSet.next()){
                    AgencyBean agencyBean = new AgencyBean();
                    agencyBean.setName(cachedRowSet.getString("Nome"));
                    System.out.println(agencyBean.getName());
                    agencyBeanList.add(agencyBean);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }

        return agencyBeanList;
    }
}
