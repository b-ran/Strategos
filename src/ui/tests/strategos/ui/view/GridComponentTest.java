package strategos.ui.view;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;


/**
 * @author Devon Mortimer (external tester)
 */
public class GridComponentTest {

    private GridComponent gridComponent;

    @BeforeEach void setup() {
        this.gridComponent = new GridComponent(null);
    }

    @Test void setEntities() {
        assertThrows(NullPointerException.class, () -> this.gridComponent.setEntities(null));
    }

    @Test void setTerrain() {
        assertThrows(NullPointerException.class, () -> this.gridComponent.setTerrain(null));
    }

    @Test void setSelection() {
        assertThrows(NullPointerException.class, () -> this.gridComponent.setSelection(null, new ArrayList<>(), null));
        assertThrows(NullPointerException.class, () -> this.gridComponent.setSelection(null, null, new ArrayList<>()));
        this.gridComponent.setSelection(null, new ArrayList<>(), new ArrayList<>());
    }
}