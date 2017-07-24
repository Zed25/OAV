package Controllers;

import DAO.ClumpDAO;
import DAO.SearchObjectDAO;
import beans.login.ClumpBean;
import beans.login.ResultBean;
import beans.login.SearchBean;
import com.sun.rowset.CachedRowSetImpl;
import enumerations.ErrorType;
import model.Clump;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrea on 01/06/17.
 */
public class FindClumpByIDController {
    private  static FindClumpByIDController instance;

    private FindClumpByIDController(){
    }

    //Adapter
    public ErrorType findClumpByIDFromBean(SearchBean searchBean, ResultBean resultBean) {
        List<Clump> clumps = findClumpByID(searchBean.getClumpID());
        if (clumps == null) {
            return ErrorType.BOAR;
        } else {
            List<ClumpBean> clumpBeans = new ArrayList<>();
            for (Clump clump : clumps) {
                ClumpBean clumpBean = new ClumpBean();
                clumpBean.setClumpID(clump.getClumpID());
                clumpBean.setBand(clump.getBand());
                clumpBean.setFluxValue(clump.getFluxValue());
                clumpBean.setGalLat(clump.getGalLat());
                clumpBean.setGalLong(clump.getGalLong());
                clumpBeans.add(clumpBean);
            }
            resultBean.setClumpBeans(clumpBeans);
            return ErrorType.NO_ERR;
        }
    }

    //KUC 5
    public List<Clump> findClumpByID(int clumpID) {
        ClumpDAO dao = new ClumpDAO();
        CachedRowSetImpl result = dao.searchClumpByID(clumpID);
        List<Clump> clumps = new ArrayList<>();
        try {
            while (result.next()) {
                Clump clump = new Clump();
                clump.setClumpID(result.getInt("clumpid"));
                clump.setBand(result.getFloat("band"));
                clump.setFluxValue(result.getFloat("value"));
                clump.setGalLat(result.getDouble("galacticlatitude"));
                clump.setGalLong(result.getDouble("galacticlongitude"));
                clumps.add(clump);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        if (clumps.isEmpty())
            clumps = null;
        return clumps;
    }

    public static synchronized FindClumpByIDController getInstance() {
        if (instance == null)
            instance = new FindClumpByIDController();
        return instance;
    }
}
