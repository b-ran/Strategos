package strategos.ui;

import strategos.model.GameState;
import strategos.model.MapLocation;
import strategos.networking.NetworkingHandler;
import strategos.ui.controller.Controller;
import strategos.ui.view.View;



/**
 * Creates the ui.
 * @author Brandon Scott-Hill
 */
public class Ui {

    private View view = null;
    private Controller controller = null;
    private NetworkingHandler networkingHandler = null;

    /**
     * Instantiates a new Ui.
     *
     * @author Brandon Scott-Hill
     * @author Daniel Pinfold
     *
     * @param model    the current gameStateModel
     *                 <dt><b>Precondition:</b><dd>
     *                 model must not be null<br>
     *                 terrain array lengths must be the same
     *
     * @param networkingHandler the handler for the network in the
     *                          multiplayer game.
     */
    public Ui(GameState model, NetworkingHandler networkingHandler) {
        assert (model != null);
        MapLocation[][] map = model.getWorld().getMap().getData();
        for (int y = 1; y < map.length; y++) {
            assert (map[0].length == map[y].length);
        }
        try {
            view = new View(model);
            view.getGridComponent().setEntities(model.getWorld().getAllUnits());
            view.getGridComponent().setTerrain(model.getWorld().getMap().getData());
            view.setSeenTerrain(model.getPlayers().get(0).getVisibleTiles());
            this.networkingHandler = networkingHandler;
            controller = new Controller(model, view, networkingHandler);
        }
        finally {
            assert (view != null && controller != null);
        }
    }

    /**
     * Instantiates a new Ui.
     *
     * @author Brandon Scott-Hill
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
     * Exits the ui.
     *
     * @author Brandon Scott-Hill
     */
    public void exit() {
        view.exit();
    }

    /**
     * Disables User Input for ui.
     *
     * @author Brandon Scott-Hill
     */
    public void disableInput() {
        controller.disableAllInput();
    }

    /**
     * Skips the menu.
     *
     * @author Brandon Scott-Hill
     */
    public void skipMenu() {
        controller.skipMenu();
    }

    /**
     * Reveals the  map.
     *
     * @author Brandon Scott-Hill
     */
    public void revealMap() {
        view.revealMap();
    }
}
