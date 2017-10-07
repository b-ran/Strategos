package strategos.ui.view;


import strategos.GameState;
import strategos.MapLocation;
import strategos.terrain.Terrain;
import strategos.ui.config.Config;
import strategos.units.Unit;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * The type View.
 */
public class View extends JComponent implements Observer {



    private JFrame frame; //Overall Frame
    private GameState model;

    private MenuComponent menuComponent = new MenuComponent();
    private MenuComponent escapeMenuComponent = new MenuComponent();
    private MenuComponent loadComponent = new MenuComponent();
    private GridComponent gridComponent = new GridComponent();
    private SideComponent sideComponent = new SideComponent();

    private JPanel menuPanel = menuComponent.setMenu();
    private JPanel escapeMenuPanel = escapeMenuComponent.setEscapeMenu();
    private JPanel loadMenuPanel = loadComponent.setLoadMenu();
    private JLayeredPane gridPanel = gridComponent.getGrid();
    private JPanel sidePanel = sideComponent.getSidePanel();

    private JPanel gamePane = new JPanel();
    private JPanel sidePane = new JPanel();
    /**
     * The game status.
     * False if game not running
     * True if game is running
     */
    private boolean game = false;

    /**
     * Instantiates a new View.
     *
     * @param model the model
     */
    public View(GameState model) {
        this.model = model;
        frame = new JFrame(Config.WINDOW_NAME);
        setMenu();
    }

    @Override
    public void update(Observable o, Object arg) {
        frame.repaint();
        gridComponent.setFocusable(true);
        gridComponent.requestFocus();
    }

    @Override
    public void repaint() {
        frame.repaint();
    }

    /**
     * Sets view as main menu.
     */
    public void setMenu() {
        removeAllComponents();
        frame.add(menuPanel);
        repack();
        game = false;
    }

    /**
     * Sets view as load menu.
     */
    public void setLoad() {
        removeAllComponents();
        frame.add(loadMenuPanel);
        repack();
        game = false;
    }

    /**
     * Adds escape menu on top of grid.
     */
    public void addEscapeMenu() {
        gridPanel.add(escapeMenuPanel,0);
        repack();
    }

    /**
     * Removes escape menu off grid.
     */
    public void removeEscapeMenu() {
        removeAllComponents();
        setGame();
    }

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
     * Status boolean.
     *
     * @return true if game is running or false if not
     */
    public boolean status() {
        return game;
    }

    /**
     * End all windows.
     */
    public void exit() {
        frame.dispose();
    }

    /**
     * Gets menu component.
     *
     * @return the menu component
     */
    public MenuComponent getMenuComponent() {
        return menuComponent;
    }

    /**
     * Gets grid component.
     *
     * @return the grid component
     */
    public GridComponent getGridComponent() {
        return gridComponent;
    }

    private void repack() {
        frame.pack();
        frame.setVisible(true);
        gridComponent.setFocusable(true);
        gridComponent.requestFocus();
    }

    /**
     * Gets escape menu component.
     *
     * @return the escape menu component
     */
    public MenuComponent getEscapeMenuComponent() {
        return escapeMenuComponent;
    }

    public SideComponent getSideComponent() {
        return sideComponent;
    }

    public MenuComponent getLoadMenuComponent() {
        return loadComponent;
    }
}