package strategos.ui.controller;

import strategos.GameState;
import strategos.MapLocation;
import strategos.terrain.Terrain;
import strategos.ui.view.GridComponent;
import strategos.ui.view.MenuComponent;
import strategos.ui.view.View;
import strategos.units.Unit;

import java.util.List;

/**
 * The type Controller.
 */
public class Controller {

    /**
     * The Model.
     */
    protected GameState model;
    /**
     * The View.
     */
    protected View view;

    protected Boolean allInput = true;


    /**
     * Instantiates a new Controller Clone.
     *
     * @param controller the controller
     */
    protected Controller(Controller controller) {
        this.model = controller.model;
        this.view = controller.view;
        this.allInput = controller.allInput;
    }

    /**
     * Instantiates a new Controller.
     *
     * @param model  the model
     * @param view   the view
     */
    public Controller(GameState model, View view) {
        this.model = model;
        this.view = view;
        setGameListeners();
        setMenuListeners();
    }

    /**
     * Sets menu listeners based on status of view.
     */
    void setMenuListeners() {
        MenuComponent m = view.getMenuComponent();
        MenuComponent e = view.getEscapeMenuComponent();
        m.getNewGameButton().addActionListener(new NewGameListener(this));
        m.getLoadButton().addActionListener(new LoadListener(this));
        m.getConnectButton().addActionListener(new ConnectListener(this));
        m.getHostButton().addActionListener(new HostListener(this));
        m.getExitButton().addActionListener(new ExitListener(this));
        e.getResumeButton().addActionListener(new ResumeListener(this));
        e.getNewGameButton().addActionListener(new NewGameListener(this));
        e.getSaveButton().addActionListener(new SaveListener(this));
        e.getLoadButton().addActionListener(new LoadListener(this));
        e.getExitButton().addActionListener(new ExitListener(this));
    }

    /**
     * Sets game listeners based on status of view.
     */
    void setGameListeners() {
        GridComponent g = view.getGridComponent();
        g.addKeyListener(new MenuListener(this));
    }

    public void disableAllInput() {
        allInput = false;
    }

    public void skipMenu() {
        view.setGame();
    }
}
