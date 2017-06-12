package csvReader;

import java.util.ArrayList;

/**
 * Created by dilettalagom on 23/05/17.
 */
public class EllipsCheck {

    double xSource=0;
    double ySource=0;

    double a=0;
    double b=0;
    double theta=0;

    boolean formula;

    boolean SorgenteEsistente( double latitudine, double longitudine, double assex, double assey, double angolo){
        this.xSource=longitudine;
        this.ySource=latitudine;
        this.a = assex;
        this.b = assey;
        this.theta=angolo;

        formula = (((Math.pow(xSource*Math.cos(theta)-ySource*Math.sin(theta),2))/Math.pow(a,2))+
                ((Math.pow(xSource*Math.sin(theta)-ySource*Math.cos(theta),2))/Math.pow(b,2)))<=1;

        System.out.println("formula: "+formula);

        return formula;
    }

}
