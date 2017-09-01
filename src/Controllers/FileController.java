package Controllers;


import enumerations.ErrorType;
import model.*;

/**
 * Created by dilettalagom on 16/04/17.
 */
public class FileController {


    private static FileController fileControllerInstance = null;
    private FileController(){}

    public static synchronized FileController getFileControllerInstance() {
        if(fileControllerInstance == null)
            fileControllerInstance = new FileController();
        return fileControllerInstance;
    }
    public String fileUploaded;
    public ErrorType errorToShow=ErrorType.NO_ERR;

    /*Creating all the entities's instances used to insert values in tables*/
    public Band createBand(double resolution){
        return new Band(resolution);
    }

    public Clump createClump(int clumpID, double galLong, double galLat, float temperature, double lmRatio, float density , int type){
        return new Clump(clumpID,galLong,galLat,temperature,lmRatio,density,type);
    }

    public Flux createFlux(Float value, Float error, Band band){
        return new Flux(value,error,band);
    }

    public Ellipse createEllipse(Integer clump, Band band, double maxaxis, double minaxis, double positionangle){
        return new Ellipse(clump, band, maxaxis, minaxis,positionangle);
    }

    public Source createSource(String sourceID, double galLong, double galLat, String comparedSource ){
        Source source;
        if (comparedSource.equals("")){
            source= new Source(sourceID, galLong, galLat);
        }else{
            source = new Source(sourceID, galLong, galLat, comparedSource);
        }
        return  source;
    }

}
