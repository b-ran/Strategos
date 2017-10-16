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

    @Test(expected = NullPointerException.class)
    public void setEntities() {
        gridComponent.setEntities(null);
    }

    @Test(expected = NullPointerException.class)
    public void setTerrain() {
        gridComponent.setTerrain(null);
    }

    @Test(expected = NullPointerException.class)
    public void setSelection() {
        gridComponent.setSelection(null, new ArrayList<>(), null);
        gridComponent.setSelection(null, null, new ArrayList<>());
        gridComponent.setSelection(null, new ArrayList<>(), new ArrayList<>());
    }
}