package strategos.ui.view;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;


/**
 * @author Devon Mortimer (external tester)
 */
public class GridComponentTest {

    private GridComponent gridComponent;

    @Before
    public void setup() {
        this.gridComponent = new GridComponent(null);
    }

//    @Test(expected = NullPointerException.class)
//    public void setEntities() {
//        gridComponent.setEntities(null);
//    }

    @Test(expected = NullPointerException.class)
    public void setTerrain() {
        gridComponent.setTerrain(null);
    }

//    @Test(expected = NullPointerException.class)
//    public void setSelection_1() {
//        gridComponent.setSelection(null, new ArrayList<>(), null);
//    }
//
//    @Test(expected = NullPointerException.class)
//    public void setSelection_2() {
//        gridComponent.setSelection(null, null, new ArrayList<>());
//    }
//
//    @Test(expected = NullPointerException.class)
//    public void setSelection_3() {
//        gridComponent.setSelection(null, new ArrayList<>(), new ArrayList<>());
//    }
}