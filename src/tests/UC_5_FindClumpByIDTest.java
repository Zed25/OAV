/**
 * Created by andrea on 23/06/17.
 */
import Controllers.FindClumpByIDController;
import model.Clump;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(value = Parameterized.class)
public class UC_5_FindClumpByIDTest {

    private Clump clump;

    @Parameterized.Parameters
    public static Collection<Clump> data() {
        Clump rightFilledClump = new Clump();
        rightFilledClump.setClumpID(179761);

        Clump errorClump1 = new Clump();
        errorClump1.setClumpID(-1);

        Clump errorClump2 = new Clump();
        errorClump2.setClumpID(0);

        return Arrays.asList(
                rightFilledClump,
                errorClump1,
                errorClump2
        );
    }

    public UC_5_FindClumpByIDTest(Clump clump) { this.clump = clump; }

    @Test
    public void findClumpTest() throws Exception {
        if (clump.getClumpID() == 179761) {
            Assert.assertNotNull("clumps is null", FindClumpByIDController.getInstance().findClumpByID(clump.getClumpID()));
            Assert.assertEquals("this is not the clump desired", (float) FindClumpByIDController.getInstance().findClumpByID(clump.getClumpID()).get(0).getClumpID(), 179761f, 1f);
        }

        else
            Assert.assertNull("clumps is null", FindClumpByIDController.getInstance().findClumpByID(clump.getClumpID()));
    }
}
