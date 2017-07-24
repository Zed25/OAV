/**
 * Created by andrea on 23/06/17.
 */
import Controllers.FindClumpByIDController;
import Controllers.RatioBetweenLinesController;
import model.Clump;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(value = Parameterized.class)
public class UC_10_RatioBetweenLinesTest {

    private Clump clump;

    @Parameterized.Parameters

    public static List<Clump> rightFilledCollection() {
        //medium value = 20
        //standard deviation = 8.16
        //median = 20
        //MAD = 10
        Clump rightFilledClump1 = new Clump();
        rightFilledClump1.setMass(10);

        Clump rightFilledClump2 = new Clump();
        rightFilledClump2.setMass(20);

        Clump rightFilledClump3 = new Clump();
        rightFilledClump3.setMass(30);

        return Arrays.asList(
                rightFilledClump1,
                rightFilledClump2,
                rightFilledClump3
        );
    }

    public static  List<Clump> clumpsError1() {
        Clump emptyClump = new Clump();
        Clump rightFilledClump1 = new Clump();
        rightFilledClump1.setMass(10);

        return Arrays.asList(
                emptyClump,
                rightFilledClump1
        );

    }

    public UC_10_RatioBetweenLinesTest(Clump clump) { this.clump = clump; }

    @Test
    public void computeMediumValueTest1() throws Exception {
        Assert.assertEquals("medium value is wrong", RatioBetweenLinesController.getInstance().computeMediumValue(rightFilledCollection()), 20, 0);
    }

    @Test
    public void computeMediumValueTest2() throws Exception {
        Assert.assertEquals("medium value is wrong", RatioBetweenLinesController.getInstance().computeMediumValue(clumpsError1()), 5, 0);
    }

    @Test
    public void computeStandardDevTest1() throws Exception {
        Assert.assertEquals("standard deviation is wrong", RatioBetweenLinesController.getInstance().computeStandardDeviation(rightFilledCollection(), 20), 8.16, 0.1);
    }

    @Test
    public void computeStandardDevTest2() throws Exception {
        Assert.assertEquals("standard deviation is wrong", RatioBetweenLinesController.getInstance().computeStandardDeviation(clumpsError1(), 5), 5, 0.1);
    }

    @Test
    public void computeMedianTest1() throws Exception {
        List<Double> massList = new ArrayList<>();
        for (Clump clump : rightFilledCollection())
            massList.add(clump.getMass());
        Assert.assertEquals("median is wrong", RatioBetweenLinesController.getInstance().computeMedian(massList), 20, 0.5);
    }

    @Test
    public void computeMedianTest2() throws Exception {
        List<Double> massList = new ArrayList<>();
        for (Clump clump : clumpsError1())
            massList.add(clump.getMass());
        Assert.assertEquals("median is wrong", RatioBetweenLinesController.getInstance().computeMedian(massList), 5, 0);
    }

    @Test
    public void computeMADTest1() throws Exception {
        Assert.assertEquals("MAD is wrong", RatioBetweenLinesController.getInstance().computeMAD(rightFilledCollection(), 20), 10, 0);
    }

    @Test
    public void computeMADTest2() throws Exception {
        Assert.assertEquals("MAD is wrong", RatioBetweenLinesController.getInstance().computeMAD(clumpsError1(), 5), 5, 0);
    }
}
