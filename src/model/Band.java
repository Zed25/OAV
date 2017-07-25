package model;

/**
 * Created by simone on 22/06/17.
 */
public class Band {

    private double resolution;

    public Band(){}
    public Band(double resolution) {
        this.resolution = resolution;
    }

    public double getResolution() {
        return resolution;
    }

    public void setResolution(double resolution) {
        this.resolution = resolution;
    }
}
