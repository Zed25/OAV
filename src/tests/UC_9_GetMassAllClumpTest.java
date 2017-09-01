/**
 * Created by andrea on 21/06/17.
 */
import model.Clump;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;

import static jdk.nashorn.internal.objects.Global.Infinity;
import static jdk.nashorn.internal.objects.Global.NaN;

@RunWith(value = Parameterized.class)
public class UC_9_GetMassAllClumpTest {
    private Clump clump;

    @Parameterized.Parameters
    public static Collection<Clump> data() {
        Clump rightFilledClump = new Clump();
        rightFilledClump.setTemperature(14.05f);
        rightFilledClump.setFluxValue(3.033f);

        Clump noTempClump = new Clump();
        noTempClump.setFluxValue(3.033f);

        Clump noFluxClump = new Clump();
        noFluxClump.setTemperature(14.05f);

        Clump blankClump = new Clump();
         return Arrays.asList(
                 rightFilledClump,
                 noFluxClump,
                 noTempClump,
                 blankClump
         );
    }

    public UC_9_GetMassAllClumpTest(Clump clump) { this.clump = clump; }

    @Test
    public void computeMassTest() throws  Exception {
        if (clump.getTemperature() == 0 && clump.getFluxValue() != 0) {
            Assert.assertEquals("there was an error", this.clump.computeMass(clump), Infinity, 0.5);
        }
        if (clump.getFluxValue() == 0 && clump.getTemperature() != 0) {
            Assert.assertEquals("there was an error", this.clump.computeMass(clump), 0.0, 0.5);
        }
        if (clump.getTemperature() == 0 && clump.getFluxValue() == 0) {
            Assert.assertEquals("there was an error", this.clump.computeMass(clump), NaN, 0.5);
        }
        if (clump.getFluxValue() != 0 && clump.getTemperature() != 0)
            Assert.assertEquals("there was an error", this.clump.computeMass(clump), 284.402, 0.5);
    }

}