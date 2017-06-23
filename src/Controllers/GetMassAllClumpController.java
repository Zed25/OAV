package Controllers;

import DAO.ClumpDAO;
import beans.login.ClumpBean;
import beans.login.search.ResultBean;
import com.sun.rowset.CachedRowSetImpl;
import enumerations.ErrorType;
import model.Clump;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrea on 01/06/17.
 */
public class GetMassAllClumpController {
    private static GetMassAllClumpController instance;

    private GetMassAllClumpController(){
    }

    //Adapter
    public ErrorType getMassAllClumpsFromBean(ResultBean resBean) {
        List<Clump> clumps = getMassAllClumps();
        if (clumps == null) {
            return ErrorType.CATERPILLAR;
        } else {
            List<ClumpBean> clumpBeans = new ArrayList<>();
            for (Clump clump : clumps) {
                ClumpBean clumpBean = new ClumpBean();
                clumpBean.setClumpID(clump.getClumpID());
                clumpBean.setMass(clump.getMass());
                clumpBeans.add(clumpBean);
            }
            resBean.setClumpBeans(clumpBeans);
            return ErrorType.NO_ERR;
        }
    }

    //KUC9
    public List<Clump> getMassAllClumps() {
        ClumpDAO dao = new ClumpDAO();
        CachedRowSetImpl result = dao.getMassAllClumps();
        List<Clump> clumps = new ArrayList<>();
        try {
            while (result.next()) {
                Clump clump = new Clump();
                clump.setClumpID(result.getInt("clumpid"));
                clump.setTemperature(result.getFloat("temperature"));
                clump.setFluxValue(result.getFloat("value"));
                clump.setMass(clump.computeMass(clump));
                clumps.add(clump);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        if (clumps.isEmpty())
            clumps = null;
        return clumps;
    }

    public static synchronized GetMassAllClumpController getInstance() {
        if (instance == null)
            instance = new GetMassAllClumpController();
        return instance;
    }
}
