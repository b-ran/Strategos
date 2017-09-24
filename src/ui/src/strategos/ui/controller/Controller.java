package strategos.ui.controller;

import strategos.terrain.Terrain;
import strategos.ui.view.*;
import strategos.ui.view.MenuComponent;
import strategos.units.Unit;

import java.awt.*;
import java.util.List;

/**
 * The type Controller.
 */
public class Controller {

    /**
     * The Entities.
     */
    protected List<Unit> entities;
    /**
     * The Terrain.
     */
    protected Terrain[][] terrain;
    /**
     * The View.
     */
    protected View view;
    private boolean start = true;

    /**
     * Instantiates a new Controller.
     *
     * @param entities the units on grid
     * @param terrain  the terrain that makes up grid
     * @param view     the view
     */
    public Controller(List<Unit> entities, Terrain[][] terrain, View view) {
        this.entities = entities;
        this.terrain = terrain;
        this.view = view;
        setMenuListeners();
    }

    /**
     * Instantiates a new Controller Clone.
     *
     * @param controller the controller
     */
    protected Controller(Controller controller) {
        this.entities = controller.entities;
        this.terrain = controller.terrain;
        this.view = controller.view;
    }

    /**
     * Sets menu listeners based on status of view.
     */
    protected void setMenuListeners() {
        MenuComponent m = view.getMenuComponent();
        MenuComponent e = view.getEscapeMenuComponent();
        if (view.status() == false) {
            m.getNewGameButton().addActionListener(new NewGameListener(this));
            m.getLoadButton().addActionListener(new LoadListener(this));
            m.getConnectButton().addActionListener(new ConnectListener(this));
            m.getHostButton().addActionListener(new HostListener(this));
            m.getExitButton().addActionListener(new ExitListener(this));
        } else {
            e.getResumeButton().addActionListener(new ResumeListener(this));
            e.getNewGameButton().addActionListener(new NewGameListener(this));
            e.getSaveButton().addActionListener(new SaveListener(this));
            e.getLoadButton().addActionListener(new LoadListener(this));
            e.getExitButton().addActionListener(new ExitListener(this));
        }
    }

    /**
     * Sets game listeners based on status of view.
     */
    protected void setGameListeners() {
        GridComponent g = view.getGridComponent();
        g.addKeyListener(new MenuListener(this));
    }
}
