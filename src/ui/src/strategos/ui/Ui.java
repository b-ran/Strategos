package strategos.ui;

import strategos.ui.controller.Controller;
import strategos.ui.view.View;
import strategos.units.*;
import strategos.terrain.*;

import java.util.ArrayList;
import java.util.List;


/**
 * The type Ui.
 */
public class Ui {

    View view = null;
    Controller controller = null;

    /**
     * Instantiates a new Ui.
     *
     * @param entities the units on the board
     * @param terrain  the terrain that makes up board
     *                 <dt><b>Precondition:</b><dd>
     *                 entities must not be null<br>
     *                 terrain must not be null<br>
     *                 terrain array lengths must be the same
     */
    public Ui(List<Unit> entities, Terrain[][] terrain) {

        assert (entities != null);
        assert (terrain != null);
        assert (terrain.length == terrain[0].length);
        for (int y = 1; y < terrain.length; y++) {
            assert (terrain[0].length == terrain[y].length);
        }
        try {
            view = new View(entities, terrain);
            controller = new Controller(entities, terrain, view);
        } finally {
            assert (view != null && controller != null);
        }
    }

    /**
     * Exit.
     */
    public void exit() {
        view.exit();
    }

    /**
     * Disables User Input.
     */
    public void disableInput() {
        controller.disableAllInput();
    }

    public void skipMenu() {
        controller.skipMenu();
    }
}
