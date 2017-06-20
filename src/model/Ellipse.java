package model;

/**
 * Created by dilettalagom on 20/06/17.
 */
public class Ellipse {

    private Integer clump;
    private double band, maxaxis, minaxis, positionangle;

    public Ellipse(Integer clump, double band, double maxaxis, double minaxis, double positionangle) {
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

    public double getBand() {
        return band;
    }

    public void setBand(double band) {
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
