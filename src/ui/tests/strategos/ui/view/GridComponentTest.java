package strategos.ui.view;


import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertThrows;


class GridComponentTest {

    private GridComponent gridComponent;

    @BeforeEach void setup() {
        gridComponent = new GridComponent();
    }

    @Test void setEntities() {
        assertThrows(NullPointerException.class, () -> gridComponent.setEntities(null));
    }

    @Test void setTerrain() {
        assertThrows(NullPointerException.class, () -> gridComponent.setTerrain(null));
    }

    @Test void setSelection() {
        assertThrows(NullPointerException.class, () -> gridComponent.setSelection(null, new ArrayList<>(), null));
        assertThrows(NullPointerException.class, () -> gridComponent.setSelection(null, null, new ArrayList<>()));
        gridComponent.setSelection(null, new ArrayList<>(), new ArrayList<>());
    }
}