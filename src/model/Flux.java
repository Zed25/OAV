package model;

/**
 * Created by dilettalagom on 20/06/17.
 */
public class Flux {

    private Float value, error;
    private Band band;

    public Flux(Float value, Band band){
        this.value=value;
        this.band=band;
    }


    public Flux(Float value, Float error, Band band){
        this.value=value;
        this.error=error;
        this.band=band;
    }

    public Float getValue() { return value; }

    public void setValue(Float value) {
        this.value = value;
    }

    public Band getBand() {
        return band;
    }

    public void setBand(Band band) {
        this.band = band;
    }

    public Float getError() {
        return error;
    }

    public void setError(Float error) {
        this.error = error;
    }

}
