package strategos.ui;

import strategos.GameState;
import strategos.MapLocation;
import strategos.UnitOwner;
import strategos.ui.controller.Controller;
import strategos.ui.view.View;



/**
 * The type Ui.
 */
public class Ui {

    View view = null;
    Controller controller = null;

    /**
     * Instantiates a new Ui.
     *
     * @param model    the current gameStateModel
     *                 <dt><b>Precondition:</b><dd>
     *                 model must not be null<br>
     *                 terrain array lengths must be the same
     */
    public Ui(GameState model) {
        assert (model != null);
        MapLocation[][] map = model.getWorld().getMap().getData();
        for (int y = 1; y < map.length; y++) {
            assert (map[0].length == map[y].length);
        }
        try {
            model.nextTurn();
            view = new View(model);
            view.getGridComponent().setEntities(model.getWorld().getAllUnits());
            view.getGridComponent().setTerrain(model.getWorld().getMap().getData());
            controller = new Controller(model, view);
        }
        finally {
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
