package model;

/**
 * Created by dilettalagom on 20/06/17.
 */
public class Flux {

    private Float value, error, band;

    public Flux(Float value, Float band){
        this.value=value;
        this.band=band;
    }


    public Flux(Float value, Float error, Float band){
        this.value=value;
        this.error=error;
        this.band=band;
    }

    public Float getValue() { return value; }

    public void setValue(Float value) {
        this.value = value;
    }

    public Float getBand() {
        return band;
    }

    public void setBand(Float band) {
        this.band = band;
    }

    public Float getError() {
        return error;
    }

    public void setError(Float error) {
        this.error = error;
    }

}
