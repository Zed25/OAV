import Controllers.SearchObjectInMapController;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by andrea on 22/07/17.
 */
/*@RunWith(value = Parameterized.class)*/
public class UC_4_SearchObjectInMapTest {

    public UC_4_SearchObjectInMapTest(){}

/*    @Parameterized.Parameters
    public static List<String> maps() {*/
    public  static String map1 = "Glimpse";
    public  static String map2 = "HiGal";
    public  static String map3 = "MIPS-GAL";

/*        return Arrays.asList(
                map1,
                map2,
                map3);
    }*/

    @Test
    public void searchObjInMapTest1() throws Exception {
        Assert.assertEquals("Some objects got lost", SearchObjectInMapController.getInstance().FindSourceInMap(map1, 0.0f).size(), 607);
    }

    @Test
    public void searchObjInMapTest2() throws Exception {
        Assert.assertEquals("Some objects got lost", SearchObjectInMapController.getInstance().FindClumpInMap(0).size(), 11222);
    }

    @Test
    public void searchObjInMapTest3() throws Exception {
        Assert.assertEquals("Some objects got lost", SearchObjectInMapController.getInstance().FindSourceInMap(map3, 0.0f).size(), 9322);
    }

}
