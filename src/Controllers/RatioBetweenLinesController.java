package Controllers;

import DAO.ClumpDAO;
import beans.login.ClumpBean;
import beans.login.search.ResultBean;
import com.sun.rowset.CachedRowSetImpl;
import enumerations.ErrorType;
import model.Clump;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by andrea on 19/06/17.
 */
public class RatioBetweenLinesController {
    private static RatioBetweenLinesController instance;

    private RatioBetweenLinesController() {
    }

    //Adapter
    public ErrorType ratioBetweenLinesFromBean(ResultBean resBean) {
        List<Clump> clumps = ratioBetweenLines();
        if (clumps == null) {
            return ErrorType.CENTIPEDE;
        } else {
            double mediumValue = computeMediumValue(clumps);
            resBean.setMediumValue(mediumValue);
            resBean.setStandardDeviation(computeStandardDeviation(clumps, mediumValue));
            List<Double> massList = new ArrayList<>();
            for (Clump clump : clumps)
                massList.add(clump.getMass());
            double median = computeMedian(massList);
            resBean.setMedian(median);
            resBean.setMAD(computeMAD(clumps, median));
            return ErrorType.NO_ERR;
        }
    }

    //KUC 10
    public List<Clump> ratioBetweenLines() {
        ClumpDAO dao = new ClumpDAO();
        CachedRowSetImpl result = dao.getMassAllClumps();
        List<Clump> clumps = new ArrayList<>();
        try {
            while (result.next()) {
                Clump clump = new Clump();
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

    public double computeMediumValue(List<Clump> clumps) {
        double mediumValue = 0.0;
        for (Clump clump : clumps) {
            mediumValue += clump.getMass();
        }
        return (mediumValue /= clumps.size());
    }

    public double computeStandardDeviation(List<Clump> clumps, double mediumValue) {
        double standardDeviation = 0.0;
        for (Clump clump : clumps) {
            standardDeviation += Math.pow(clump.getMass() - mediumValue, 2.0);
        }
        standardDeviation /= clumps.size();
        return Math.sqrt(standardDeviation);
    }

    public double computeMedian(List<Double> list) {
        Collections.sort(list);

        //# of elements is odd
        if (list.size() % 2 == 1)
            return list.get((list.size() + 1)/2 - 1);

            //# of elements is even
        else
            return  (list.get(list.size()/2 - 1) + list.get(list.size()/2))/2;
    }

    public double computeMAD(List<Clump> clumps, double median) {
        List<Double> list = new ArrayList<>();
        for (int i = 0; i < clumps.size(); i++) {
            list.add(Math.abs(clumps.get(i).getMass() - median));
        }
        return computeMedian(list);
    }

    public static synchronized RatioBetweenLinesController getInstance() {
        if (instance == null)
            instance = new RatioBetweenLinesController();
        return instance;
    }
}
