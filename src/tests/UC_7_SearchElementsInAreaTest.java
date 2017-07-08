import beans.login.ClumpBean;
import beans.login.SquareCircleSearchBean;
import beans.login.UserBean;
import enumerations.ErrorType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by simone on 08/07/17.
 */
@RunWith(value = Parameterized.class)
public class UC_7_SearchElementsInAreaTest {
    private SquareCircleSearchBean squareCircleSearchBean;

    @Parameterized.Parameters
    public static Collection<SquareCircleSearchBean> data() {

        //clumps in square query
        SquareCircleSearchBean clumpInSquare = new SquareCircleSearchBean();
        clumpInSquare.setnElements("500");
        clumpInSquare.setAreaType("Square");
        clumpInSquare.setElementType("Clumps");
        clumpInSquare.setBaseLenght("2000");
        clumpInSquare.setGalLat("0.017481");
        clumpInSquare.setGalLong("-0.23331223");

        //clumps in circle
        SquareCircleSearchBean clumpInCircle = new SquareCircleSearchBean();
        clumpInCircle.setnElements("300");
        clumpInCircle.setAreaType("Circle");
        clumpInCircle.setElementType("Clumps");
        clumpInCircle.setBaseLenght("2000");
        clumpInCircle.setGalLat("0.017481");
        clumpInCircle.setGalLong("-0.23331223");

        //sources in square
        SquareCircleSearchBean sourcesInSquare = new SquareCircleSearchBean();
        sourcesInSquare.setnElements("500");
        sourcesInSquare.setAreaType("Square");
        sourcesInSquare.setElementType("Sources");
        sourcesInSquare.setBaseLenght("2000");
        sourcesInSquare.setGalLat("0.017481");
        sourcesInSquare.setGalLong("-0.23331223");

        //sources in circle
        SquareCircleSearchBean sourcesInCircle = new SquareCircleSearchBean();
        sourcesInCircle.setnElements("300");
        sourcesInCircle.setAreaType("Circle");
        sourcesInCircle.setElementType("Sources");
        sourcesInCircle.setBaseLenght("2000");
        sourcesInCircle.setGalLat("0.017481");
        sourcesInCircle.setGalLong("-0.23331223");

        return Arrays.asList(
                clumpInSquare,
                clumpInCircle,
                sourcesInSquare,
                sourcesInCircle
        );
    }

    public UC_7_SearchElementsInAreaTest(SquareCircleSearchBean squareCircleSearchBean) {
        this.squareCircleSearchBean = squareCircleSearchBean;
    }

    @Test
    public void searchElementsInArea() throws Exception {

        Assert.assertEquals("this test must be passed, but it isn't", ErrorType.NO_ERR,
                squareCircleSearchBean.searchElementsInArea());
    }
}
