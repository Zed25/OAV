/**
 * Created by dilettalagom on 08/07/17.
 */
import Controllers.YoungSourceController;
import model.Clump;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(value = Parameterized.class)
public class UC_11_FindYoungSourcesTest {

    private Clump clump;

    @Parameterized.Parameters
    public static Collection<Clump> data(){
        Clump testSuccessClump = new Clump();
        testSuccessClump.setClumpID(179761); //clump with sources

        Clump testErrorClump = new Clump();
        testErrorClump.setClumpID(178173); //clump with no sources

        Clump testErrorClump1 = new Clump();//no existing clump

        return Arrays.asList(testSuccessClump, testErrorClump, testErrorClump1);
    }

    public UC_11_FindYoungSourcesTest(Clump clump){
        this.clump=clump;
    }

    @Test
    public void findYoungSource1() throws Exception {
        Assert.assertNotNull("the selected clumps is null", YoungSourceController.getInstance().findYoungSource(clump.getClumpID()));
    }

    @Test
    public void findYoungSource2() throws Exception {
        Assert.assertEquals("the selected clumps has no young sources", YoungSourceController.getInstance().findYoungSource(clump.getClumpID()));
    }



}
