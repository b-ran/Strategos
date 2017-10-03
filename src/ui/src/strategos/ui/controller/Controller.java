package strategos.ui.controller;

import strategos.GameBoard;
import strategos.GameState;
import strategos.MapLocation;
import strategos.terrain.Terrain;
import strategos.ui.view.GridComponent;
import strategos.ui.view.MenuComponent;
import strategos.ui.view.View;
import strategos.units.Unit;

import java.awt.*;
import java.util.List;

import static strategos.ui.config.Config.HEX_SIZE;

/**
 * The type Controller.
 */
public class Controller {

    /**
     * The Model.
     */
    protected GameState model;

    /**
     * The Board.
     */
    GameBoard board;
    /**

    /**
     * The View.
     */
    protected View view;

    MapLocation selectedMapLocation;
    Boolean allInput = true;
    boolean menuToggle = false;


    /**
     * Instantiates a new Controller Clone.
     *
     * @param controller the controller
     */
    protected Controller(Controller controller) {
        this.model = controller.model;
        this.view = controller.view;
        this.board = controller.board;
        this.selectedMapLocation = controller.selectedMapLocation;
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
        board = model.getWorld().getMap();
        setGameListeners();
        setMenuListeners();
    }

    /**
     * Sets menu listeners based on status of view.
     */
    private void setMenuListeners() {
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
    private void setGameListeners() {
        GridComponent g = view.getGridComponent();
        g.addKeyListener(new MenuListener(this));
        g.addMouseListener(new SelectListener(this));
        g.addMouseMotionListener(new SelectListener(this));
    }

    Point getHexPos(int x, int y) {
        Point p = new Point();
        p.y = getHexY(y);
        if (p.y % 2 != 0) {
            x-=HEX_SIZE/2;
        }
        p.x = getHexX(x);
        if (p.x > board.getData()[0].length-1) {
            p.x = board.getData()[0].length-1;
        } else if (p.x < 0) {
            p.x = 0;
        }
        if (p.y > board.getData().length-1) {
            p.y = board.getData().length-1;
        }  else if (p.y < 0) {
            p.y = 0;
        }
        return p;
    }


    private int getHexX(int x) {
        return x / (HEX_SIZE) - 1;
    }

    private int getHexY(int y) {
        Double d = (1.3 * y) / (HEX_SIZE);
        return d.intValue();
    }


    public void disableAllInput() {
        allInput = false;
    }

    public void skipMenu() {
        view.setGame();
    }

    void menuToggle() {
        menuToggle = !menuToggle;
    }

    boolean getMenuToggle() {
        return menuToggle;
    }
}
