package strategos.ui.controller;

import strategos.GameBoard;
import strategos.GameState;
import strategos.MapLocation;
import strategos.UnitOwner;
import strategos.networking.NetworkingHandler;
import strategos.ui.view.GridComponent;
import strategos.ui.view.MenuComponent;
import strategos.ui.view.SideComponent;
import strategos.ui.view.View;
import strategos.units.Bridge;
import strategos.units.Unit;

import java.awt.*;
import java.util.List;

import static strategos.ui.config.Config.HEX_SIZE;

/**
 * The type Controller.
 */
public class Controller {

    private NetworkingHandler networkingHandler;
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

    private MapLocation selectedMapLocation;
    private Unit selectedUnit;
    private List<MapLocation> tilesInMoveRange;
    private List<Unit> unitsInAttackRange;
    private boolean selectionToggle = true;

    Boolean allInput = true;
    private boolean menuToggle = false;
    UnitOwner uiOwner;

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
        this.uiOwner = controller.uiOwner;
    }

    /**
     * Instantiates a new Controller.
     *
     * @param model  the model
     * @param view   the view
     */
    public Controller(GameState model, View view, NetworkingHandler networkingHandler) {
        this.model = model;
        this.networkingHandler = networkingHandler;
        this.view = view;
        uiOwner = view.getUiOwner();
        board = model.getWorld().getMap();
        setGameListeners();
        setMenuListeners();
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
        uiOwner = view.getUiOwner();
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
        MenuComponent l = view.getLoadMenuComponent();

        m.getNewGameButton().addActionListener(new NewGameListener(this));
        m.getLoadButton().addActionListener(new LoadListener(this));
        m.getConnectButton().addActionListener(new ConnectListener(this, networkingHandler));
        m.getHostButton().addActionListener(new HostListener(this, networkingHandler));
        m.getExitButton().addActionListener(new ExitListener(this));

        for (int i = 1; i <= 3; i++) {
            l.getSaveSlot(i).addActionListener(new LoadSlotListener(this, i));
        }
        l.getExitButton().addActionListener(new BackListener(this));

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
        SideComponent s = view.getSideComponent();
        g.addKeyListener(new MenuListener(this));
        g.addMouseListener(new SelectListener(this));
        g.addMouseMotionListener(new SelectListener(this));
        g.addMouseListener(new MoveListener(this));
        g.addMouseListener(new AttackListener(this));
        s.getNextTurnButton().addActionListener(new NextTurnListener(this));
        s.getEntrenchButton().addActionListener(new EntrenchListener(this));
        s.getWaryButton().addActionListener(new WaryListener(this));
    }

    Point getHexPos(int x, int y) {
        Point p = new Point();
        p.y = getHexY(y);
        p.x = getHexX(x - ((p.y % 2 != 0) ? HEX_SIZE / 2 : 0)) - p.y / 2;
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

    MapLocation getSelectedMapLocation() {
        return selectedMapLocation;
    }

    public NetworkingHandler getNetworkingHandler() {
        return networkingHandler;
    }

    private boolean mapLocationIn(MapLocation location, List<MapLocation> otherLocations) {
        for (MapLocation other : otherLocations) {
            if (other.getX() == location.getX() && other.getY() == location.getY()) {
                return true;
            }
        }
        return false;
    }

    void setSelectedMapLocation(MapLocation selectedMapLocation) {

        if (this.selectedMapLocation != null && selectedMapLocation != null) {
            if (selectedUnit != null) {
                if (mapLocationIn(selectedMapLocation, model.getTilesInRange(selectedUnit.getPosition(), 1)) &&
                        selectedUnit.getOwner() == model.getCurrentTurn()) {
                    handleCommand(selectedMapLocation);
                } else {
                    selectionToggle = true;
                    selectedUnit = model.getUnitAt(selectedMapLocation);
                }
            }
        }

        this.selectedMapLocation = selectedMapLocation;
        selectedUnit = model.getUnitAt(this.selectedMapLocation);
        if (selectedMapLocation == null) {
            selectionHelper();
            return;
        }

        if (selectedUnit == null) {
            selectionToggle = false;
            view.getGridComponent().setSelection(selectedMapLocation);
            view.getSideComponent().setSelection(selectedMapLocation, null);
        } else {
            selectionToggle = true;
            unitsInAttackRange = model.getUnitsInAttackRange(selectedUnit);
            tilesInMoveRange = model.getTilesInMoveRange(selectedUnit);
            view.getGridComponent().setSelection(selectedMapLocation, unitsInAttackRange,  tilesInMoveRange);
            view.getSideComponent().setSelection(selectedMapLocation, selectedUnit);
        }
        view.repaint();
    }

    private void handleCommand(MapLocation newLocation) {
        if (model.getUnitAt(newLocation) == null ||
                (model.getUnitAt(newLocation) instanceof Bridge &&
                 model.getUnitAt(newLocation).getOwner() == selectedUnit.getOwner())

                        && model.getPlayers().indexOf(selectedUnit.getOwner()) == model.getPlayers().indexOf(uiOwner)) {
            model.move(selectedUnit, newLocation);
        } else {
            model.attack(selectedUnit, newLocation);
        }
    }

    private void selectionHelper() {
        selectionToggle = true;
        selectedUnit = null;
        view.getGridComponent().setSelection(null);
        view.getSideComponent().setSelection(null, null);
        view.repaint();
    }

    boolean getSelectionToggle() {
        return selectionToggle;
    }

    Unit getSelectedUnit() {
        return selectedUnit;
    }

    List<MapLocation> getTilesInMoveRange() {
        return tilesInMoveRange;
    }

    List<Unit> getUnitsInAttackRange() {
        return unitsInAttackRange;
    }

    void setSelectionToggle(boolean selectionToggle) {
        this.selectionToggle = selectionToggle;
    }
}
