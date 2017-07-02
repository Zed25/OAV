package model;

/**
 * Created by dilettalagom on 20/06/17.
 */
public class Ellipse {

    private Integer clump;
    private double maxaxis, minaxis, positionangle;
    private Band band;

    public Ellipse(Integer clump, Band band, double maxaxis, double minaxis, double positionangle) {
        this.clump=clump;
        this.band=band;
        this.maxaxis=maxaxis;
        this.minaxis=minaxis;
        this.positionangle=positionangle;
    }

    public Integer getClump() {
        return clump;
    }

    public void setClump(Integer clump) {
        this.clump = clump;
    }

    public Band getBand() {
        return band;
    }

    public void setBand(Band band) {
        this.band = band;
    }

    public double getMaxaxis() {
        return maxaxis;
    }

    public void setMaxaxis(double maxaxis) {
        this.maxaxis = maxaxis;
    }

    public double getMinaxis() {
        return minaxis;
    }

    public void setMinaxis(double minaxis) {
        this.minaxis = minaxis;
    }

    public double getPositionangle() {
        return positionangle;
    }

    public void setPositionangle(double positionangle) {
        this.positionangle = positionangle;
    }
}
