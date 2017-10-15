package strategos.ui.view;


import strategos.model.GameState;
import strategos.model.MapLocation;
import strategos.model.UnitOwner;
import strategos.ui.config.Config;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import static strategos.ui.config.Config.*;

/**
 * The type View.
 *
 * @author Brandon Scott-Hill - scotthbran
 * @author Daniel Pinfold - pinfoldani
 */
public class View extends JComponent implements Observer {



    private JFrame frame; //Overall Frame
    /**
     * The Model.
     */
    GameState model;
    private UnitOwner uiOwner;

    private MenuComponent menuComponent = new MenuComponent();
    private MenuComponent escapeMenuComponent = new MenuComponent();
    private MenuComponent loadComponent = new MenuComponent();
    private GridComponent gridComponent = new GridComponent(this);
    private SideComponent sideComponent = new SideComponent(this);

    private JPanel menuPanel = menuComponent.setMenu();
    private JPanel escapeMenuPanel = escapeMenuComponent.setEscapeMenu();
    private JPanel loadMenuPanel = loadComponent.setLoadMenu();
    private JLayeredPane gridPanel = gridComponent.getGrid();
    private JPanel sidePanel = sideComponent.getSidePanel();

    private JPanel instructionPane = new JPanel();
    private JPanel gamePane = new JPanel();
    private JPanel sidePane = new JPanel();

    private List<MapLocation> seenTerrain = new ArrayList<>();

    private boolean firstTurn = true;

    /**
     * The game status.
     * False if game not running
     * True if game is running
     */
    private boolean game = false;



    /**
     * If escapeMenu is being displayed.
     * False if escapeMenu not running
     * True if escapeMenu is running
     */
    private boolean escapeMenu = false;

    /**
     * If map the map is revealed.
     * False if not revealed
     * True if revealed
     */
    private boolean revealMap = false;

    /**
     * Instantiates a new View.
     *
     * @param model the model
     * @author Brandon Scott-Hill
     */
    public View(GameState model) {
        this.model = model;
        model.addObserver(this);
        this.uiOwner = model.getCurrentTurn();
        frame = new JFrame(Config.WINDOW_NAME);

        JTextArea message = new JTextArea();
        message.setText(GAME_INSTRUCTION_MESSAGE);
        message.setLineWrap(true);
        message.setWrapStyleWord(true);
        message.setPreferredSize(new Dimension(600, 700));
        instructionPane.add(message);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMenu();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (model.getWorld().getAllUnits() != gridComponent.getEntities()) {

            if (model.getWinner() > 0) {
                JOptionPane.showMessageDialog(getGridComponent(), "Player " + model.getWinner() + " wins!\n" +
                        "Game took " + model.getNumberTurns() + " turns");
            }

            gridComponent.setEntities(model.getWorld().getAllUnits());
            gridComponent.setTerrain(model.getWorld().getMap().getData());
            if (!revealMap) {
                setSeenTerrain(getUiOwner().getVisibleTiles());
            }
            if (!firstTurn) {
                JOptionPane.showMessageDialog(getGridComponent(), "It's your turn");
                sideComponent.setPlayerText(PLAYER_NAME);
            }
            setFirstTurn(false);
        }
        gridComponent.requestFocus();
        gridComponent.setFocusable(true);
        frame.repaint();
    }

    @Override
    public void repaint() {
        frame.repaint();
    }

    /**
     * Sets view as main menu.
     *
     * @author Brandon Scott-Hill
     */
    public void setMenu() {
        removeAllComponents();
        frame.add(menuPanel);
        repack();
        game = false;
    }

    /**
     * Sets view as load menu.
     *
     * @author Daniel Pinfold
     */
    public void setLoad() {
        removeAllComponents();
        frame.add(loadMenuPanel);
        loadMenuPanel.setPreferredSize(frame.getSize());
        repack();
        game = false;
    }

    /**
     * Sets view as instruction.
     *
     * @author Daniel Pinfold
     */
    public void setInstruction() {
        JOptionPane.showMessageDialog(menuPanel, instructionPane);
        instructionPane.setPreferredSize(GAME_INSTRUCTION_BOX_DIMENSIONS);
        repack();
    }

    /**
     * Adds escape menu on top of grid.
     *
     * @author Brandon Scott-Hill
     */
    public void addEscapeMenu() {
        gridPanel.add(escapeMenuPanel,0);
        repack();
        escapeMenu = true;
    }

    /**
     * Removes escape menu off grid.
     *
     * @author Brandon Scott-Hill
     */
    public void removeEscapeMenu() {
        removeAllComponents();
        setGame();
        escapeMenu = false;
    }

    /**
     * Removes all components.
     *
     * @author Brandon Scott-Hill
     */
    private void removeAllComponents() {
        gridPanel.remove(escapeMenuPanel);
        gridPanel.remove(gridComponent);
        sidePane.remove(sideComponent);
        sidePane.remove(sidePanel);
        gamePane.remove(sidePane);
        frame.remove(gamePane);
        frame.remove(menuPanel);
        frame.remove(loadMenuPanel);
    }

    /**
     * Sets view as game.
     *
     * @author Brandon Scott-Hill
     */
    public void setGame() {
        removeAllComponents();
        gamePane = new JPanel();
        gamePane.setLayout(new BorderLayout());

        gamePane.add(gridPanel,BorderLayout.CENTER);
        gridPanel.add(gridComponent,1);

        sidePane = new JPanel();
        sidePane.setLayout(new BoxLayout(sidePane,BoxLayout.Y_AXIS));

        sidePane.add(sideComponent);
        sidePane.add(sidePanel);

        gamePane.add(sidePane, BorderLayout.EAST);
        frame.add(gamePane);
        repack();
        game = true;
    }

    /**
     * Status on the game.
     *
     * @author Brandon Scott-Hill
     *
     * @return true if game is running or false if not
     */
    public boolean status() {
        return game;
    }

    /**
     * End all windows.
     *
     * @author Brandon Scott-Hill
     */
    public void exit() {
        frame.dispose();
    }

    /**
     * Gets menu component.
     *
     * @author Brandon Scott-Hill
     *
     * @return the menu component
     */
    public MenuComponent getMenuComponent() {
        return menuComponent;
    }

    /**
     * Gets grid component.
     *
     * @author Brandon Scott-Hill
     *
     * @return the grid component
     */
    public GridComponent getGridComponent() {
        return gridComponent;
    }


    /**Repacks the UI, called when panels are being changed
     *
     * @author Brandon Scott-Hill
     */
    private void repack() {
        frame.pack();
        frame.setVisible(true);
        gridComponent.setFocusable(true);
        gridComponent.requestFocus();
    }

    /**
     * Gets escape menu component.
     *
     * @author Brandon Scott-Hill
     *
     * @return the escape menu component
     */
    public MenuComponent getEscapeMenuComponent() {
        return escapeMenuComponent;
    }

    /**
     * Gets side component.
     *
     * @author Brandon Scott-Hill
     *
     * @return the side component
     */
    public SideComponent getSideComponent() {
        return sideComponent;
    }

    /**
     * Gets load menu component.
     *
     * @author Brandon Scott-Hill
     *
     * @return the load menu component
     */
    public MenuComponent getLoadMenuComponent() {
        return loadComponent;
    }

    /**
     * Gets ui owner.
     *
     * @author Brandon Scott-Hill
     *
     * @return the ui owner
     */
    public UnitOwner getUiOwner() {
        return model.getThisInstancePlayer();
    }

    /**
     * Sets if it's first turn or not.
     *
     * @author Daniel Pinfold
     *
     * @param firstTurn if it's first turn or not.
     */
    public void setFirstTurn(boolean firstTurn) {
        this.firstTurn = firstTurn;
    }

    /**
     * Is view is display the escapeMenu.
     *
     * @author Brandon Scott-Hill
     *
     * @return if escapeMenu is being displayed
     */
    public boolean isEscapeMenu() {
        return escapeMenu;
    }

    /**
     * Gets seen terrain.
     *
     * @author Brandon Scott-Hill
     *
     * @return the seen terrain
     */
    List<MapLocation> getSeenTerrain() {
        return seenTerrain;
    }

    /**
     * Sets seen terrain.
     *
     * @author Brandon Scott-Hill
     *
     * @param seenTerrain the seen terrain
     */
    public void setSeenTerrain(List<MapLocation> seenTerrain) {
        this.seenTerrain = seenTerrain;
    }

    /**
     * Reveals the map.
     *
     * @author Brandon Scott-Hill
     */
    public void revealMap() {
        revealMap = true;
        getGridComponent().revealMap();
    }


    /**
     * Get the number of turns.
     *
     * @author Daniel Pinfold
     */
    int getNumberTurns() {
        return model.getNumberTurns();
    }
}