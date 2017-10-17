package strategos.ui.controller;

import strategos.model.GameBoard;
import strategos.model.GameState;
import strategos.model.MapLocation;
import strategos.model.UnitOwner;
import strategos.networking.NetworkingHandler;
import strategos.ui.view.GridComponent;
import strategos.ui.view.MenuComponent;
import strategos.ui.view.SideComponent;
import strategos.ui.view.View;
import strategos.units.Unit;

import java.awt.*;
import java.util.List;

import static strategos.ui.config.Config.HEX_SIZE;

/**
 * The Controller for the ui.
 *
 * @author Brandon Scott-Hill
 */
public class Controller {

    private NetworkingHandler networkingHandler;
    protected GameState model;
    GameBoard board;
    protected View view;

    private MapLocation selectedMapLocation;
    private Unit selectedUnit;
    private List<MapLocation> tilesInMoveRange;
    private List<Unit> unitsInAttackRange;
    private boolean selectionToggle = true;

    Boolean allInput = true;
    private boolean menuToggle = false;
    private UnitOwner uiOwner;

    /**
     * Instantiates a new Controller Clone.
     *
     * @author Brandon Scott-Hill
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
     * @author Brandon Scott-Hill
     *
     * @param model             the model
     * @param view              the view
     * @param networkingHandler the networking handler
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
     * Instantiates a new Controller Without Networking.
     *
     * @author Brandon Scott-Hill
     *
     * @param model the model
     * @param view  the view
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
     *
     * @author Brandon Scott-Hill
     */
    private void setMenuListeners() {
        MenuComponent m = view.getMenuComponent();
        MenuComponent e = view.getEscapeMenuComponent();
        MenuComponent l = view.getLoadMenuComponent();

        m.getConnectButton().addActionListener(new ConnectListener(this, networkingHandler));
        m.getHostButton().addActionListener(new HostListener(this, networkingHandler));
        m.getHowToPlayButton().addActionListener(new HowToPlayListener(this));
        m.getExitButton().addActionListener(new ExitListener(this));

        for (int i = 1; i <= 3; i++) {
            l.getSaveSlot(i).addActionListener(new LoadSlotListener(this, i));
        }
        l.getExitButton().addActionListener(new BackListener(this));

        e.getHowToPlayButton().addActionListener(new HowToPlayListener(this));
        e.getResumeButton().addActionListener(new ResumeListener(this));
        e.getNewGameButton().addActionListener(new NewGameListener(this));
        e.getSaveButton().addActionListener(new SaveListener(this));
        e.getLoadButton().addActionListener(new LoadListener(this));
        e.getExitButton().addActionListener(new ExitListener(this));
    }

    /**
     * Sets game listeners based on status of view.
     *
     * @author Brandon Scott-Hill
     */
    private void setGameListeners() {
        GridComponent g = view.getGridComponent();
        SideComponent s = view.getSideComponent();
        g.addKeyListener(new MenuListener(this));
        g.addMouseListener(new SelectListener(this));
        g.addMouseMotionListener(new SelectListener(this));
        g.addMouseListener(new MoveSelectListener(this));
        g.addMouseMotionListener(new MoveSelectListener(this));
        g.addMouseListener(new AttackSelectListener(this));
        //g.addMouseWheelListener(new CameraListener(this));
        s.getNextTurnButton().addActionListener(new NextTurnListener(this));
        s.getEntrenchButton().addActionListener(new EntrenchListener(this));
        s.getWaryButton().addActionListener(new WaryListener(this));
        s.getAttackButton().addActionListener(new AttackListener(this));
    }

    /**
     * Gets hex pos.
     *
     * @author Brandon Scott-Hill
     *
     * @param x the x
     * @param y the y
     * @return the hex pos
     */
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


    /**
     * Disable all input.
     *
     * @author Brandon Scott-Hill
     */
    public void disableAllInput() {
        allInput = false;
    }

    /**
     * Skip the menu.
     *
     * @author Brandon Scott-Hill
     */
    public void skipMenu() {
        view.setGame();
    }

    /**
     * Toggle the status of the menu.
     *
     * @author Brandon Scott-Hill
     */
    void menuToggle() {
        menuToggle = !menuToggle;
    }

    /**
     * Gets status of the menu.
     *
     * @author Brandon Scott-Hill
     *
     * @return the menu toggle
     */
    boolean getMenuToggle() {
        return menuToggle;
    }

    /**
     * Gets selected map location.
     *
     * @author Brandon Scott-Hill
     *
     * @return the selected map location
     */
    MapLocation getSelectedMapLocation() {
        return selectedMapLocation;
    }

    /**
     * Gets networking handler.
     *
     * @author Brandon Scott-Hill
     *
     * @return the networking handler
     */
    NetworkingHandler getNetworkingHandler() {
        return networkingHandler;
    }

    /**
     * Sets selected map location.
     *
     * @author Brandon Scott-Hill
     *
     * @param selectedMapLocation the selected map location
     */
    void setSelectedMapLocation(MapLocation selectedMapLocation) {
        GridComponent g = view.getGridComponent();
        SideComponent s = view.getSideComponent();

        if (checkMoveAttack(selectedMapLocation)) return;

        this.selectedMapLocation = selectedMapLocation;
        this.selectedUnit = model.getUnitAt(selectedMapLocation);

        if (selectedUnit == null) {
            if (!selectionToggle) {
                resetSection();
                return;
            }
            s.setSelection(selectedMapLocation,null);
            g.setSelection(selectedMapLocation);

        } else {
            List<MapLocation> tilesInMoveRange = model.getTilesInMoveRange(selectedUnit);
            List<Unit> unitsInAttackRange = model.getUnitsInAttackRange(selectedUnit);

            if (this.selectedUnit.getOwner() != view.getUiOwner()) {
                s.setSelection(selectedMapLocation, this.selectedUnit);
                g.setSelection(selectedMapLocation);
                return;
            }

            s.setSelection(selectedMapLocation, this.selectedUnit);
            g.setSelection(selectedMapLocation, unitsInAttackRange, tilesInMoveRange);
            selectionToggle = false;
        }
    }

    private boolean checkMoveAttack(MapLocation selectedMapLocation) {
        if (selectedUnit == null || selectedMapLocation == null || this.selectedUnit.getOwner() != view.getUiOwner()) return false;

        List<MapLocation> tilesInMoveRange = model.getTilesInMoveRange(selectedUnit);
        List<Unit> unitsInAttackRange = model.getUnitsInAttackRange(selectedUnit);

        for (MapLocation m : tilesInMoveRange) {
            if (m.getX() == selectedMapLocation.getX() && m.getY() == selectedMapLocation.getY()) {
                return false;
            }
        }

        for (Unit u : unitsInAttackRange) {
            MapLocation m = u.getPosition();
            if (m.equals(selectedMapLocation)) {
                return false;
            }
        }

        return false;
    }

    /**
     * Reset section.
     *
     * @author Brandon Scott-Hill
     */
    void resetSection() {
        selectionToggle = true;
        selectedUnit = null;
        view.getGridComponent().setSelection(null);
        view.getSideComponent().setSelection(null, null);
        view.repaint();
    }


    /**
     * Gets selected unit.
     *
     * @author Brandon Scott-Hill
     *
     * @return the selected unit
     */
    Unit getSelectedUnit() {
        return selectedUnit;
    }


    /**
     * Sets selection toggle.
     *
     * @author Brandon Scott-Hill
     *
     * @param selectionToggle the selection toggle
     */
    void setSelectionToggle(boolean selectionToggle) {
        this.selectionToggle = selectionToggle;
    }


}
