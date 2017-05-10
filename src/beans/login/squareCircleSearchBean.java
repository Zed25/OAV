package beans.login;

import Controllers.SearchController;
import enumerations.ErrorType;

import java.util.List;

/**
 * Created by simone on 02/05/17.
 */
public class squareCircleSearchBean {

    private String nElements, elementType, areaType, baseLenght, galLat, galLong;
    private List<ClumpBean> resultClumps;
    private List<SourceBean> resultSources;

    public squareCircleSearchBean() {
        this.nElements = "300";
        this.elementType = "";
        this.areaType = "";
        this.baseLenght = "";
        this.galLat = "";
        this.galLong = "";
    }

    /**
     * check if the bean's object is full
     * @return boolean
     */
    public boolean isFull(){
        if(this.getnElements().equals("") || this.getElementType().equals("") || this.getAreaType().equals("") || this.getBaseLenght().equals(""))
            return false;
        return true;
    }

    /**
     * clean the bean's object
     */
    public void emptyBean(){
        this.setAreaType("");
        this.setBaseLenght("");
        this.setElementType("");
        this.setnElements("");
    }

    public int getMaxClumpSourcesNumber(){
        return SearchController.getInstance().getMaxClumpSourcesNumber();
    }

    public ErrorType searchElementsInArea() {
        return SearchController.getInstance().searchElementsInArea(this);
    }

    public String getnElements() {
        return nElements;
    }

    public void setnElements(String nElements) {
        this.nElements = nElements;
    }

    public String getElementType() {
        return elementType;
    }

    public void setElementType(String elementType) {
        this.elementType = elementType;
    }

    public String getAreaType() {
        return areaType;
    }

    public void setAreaType(String areaType) {
        this.areaType = areaType;
    }

    public String getBaseLenght() {
        return baseLenght;
    }

    public void setBaseLenght(String baseLenght) {
        this.baseLenght = baseLenght;
    }

    public String getGalLat() {
        return galLat;
    }

    public void setGalLat(String galLat) {
        this.galLat = galLat;
    }

    public String getGalLong() {
        return galLong;
    }

    public void setGalLong(String galLong) {
        this.galLong = galLong;
    }

    public List<ClumpBean> getResultClumps() {
        return resultClumps;
    }

    public void setResultClumps(List<ClumpBean> resultClumps) {
        this.resultClumps = resultClumps;
    }

    public List<SourceBean> getResultSources() {
        return resultSources;
    }

    public void setResultSources(List<SourceBean> resultSources) {
        this.resultSources = resultSources;
    }
}
