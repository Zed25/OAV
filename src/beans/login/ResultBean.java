package beans.login;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrea on 31/03/17.
 */
public class ResultBean {

    private List<ClumpBean> clumpBeans;
    private List<SourceBean> sourceBeans;

    //counts all the results shown in the jsp result page
    private int count;
    //counts the show page number
    private int page;
    //UC 10
    private double mediumValue;
    private double standardDeviation;
    private double median;
    private double MAD;

    private double percClumpPop;

    public ResultBean() {

    }

    public double getMediumValue() {
        return mediumValue;
    }

    public void setMediumValue(double mediumValue) {
        this.mediumValue = mediumValue;
    }

    public double getStandardDeviation() {
        return standardDeviation;
    }

    public void setStandardDeviation(double standardDeviation) {
        this.standardDeviation = standardDeviation;
    }

    public double getMedian() {
        return median;
    }

    public void setMedian(double median) {
        this.median = median;
    }

    public double getMAD() {
        return MAD;
    }

    public void setMAD(double MAD) {
        this.MAD = MAD;
    }

    public List<ClumpBean> getClumpBeans() { return clumpBeans; }

    public void setClumpBeans(List<ClumpBean> clumpBeans) { this.clumpBeans = clumpBeans; }

    public List<SourceBean> getSourceBeans() { return sourceBeans; }

    public void setSourceBeans(List<SourceBean> sourceBeans) { this.sourceBeans = sourceBeans; }

    public int getCount() { return count; }

    public void setCount(int count) { this.count = count; }

    public void incrementCount() {
        this.count ++;
    }

    public void reset() {
        this.count = 0;
        this.page = 1;
    }

    public void dropAllData() {
        this.reset();
        List<ClumpBean> clumpBeans = new ArrayList<>();
        this.setClumpBeans(clumpBeans);
        List<SourceBean> sourceBeans = new ArrayList<>();
        this.setSourceBeans(sourceBeans);
        this.setMediumValue(0.0);
        this.setMedian(0.0);
        this.setMAD(0.0);
        this.setStandardDeviation(0.0);
    }

    public double getPercClumpPop() {
        return percClumpPop;
    }

    public void setPercClumpPop(double percClumpPop) {
        this.percClumpPop = percClumpPop;
    }
}
