package strategos.ui.view;


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

    /**
     * The units on the grid.
     */
    protected List<Unit> entities;

    /**
     * The terrain that makes up the grid.
     */
    protected Terrain[][] terrain;

    private MenuComponent menuComponent = new MenuComponent();
    private MenuComponent escapeMenuComponent = new MenuComponent();
    private GridComponent gridComponent = new GridComponent();
    private SideComponent sideComponent = new SideComponent();

    private JPanel menuPanel = menuComponent.setMenu();
    private JPanel escapeMenuPanel = escapeMenuComponent.setEscapeMenu();
    private JLayeredPane gridPanel = gridComponent.getGrid();
    private JPanel sidePanel = sideComponent.getSidePanel();

    /**
     * The game status.
     * False if game not running
     * True if game is running
     */
    protected boolean game = false;

    /**
     * Instantiates a new View.
     *
     * @param entities the untis on the grid
     * @param terrain  the terrain that makes up the grid
     */
    public View(List<Unit> entities, Terrain[][] terrain) {
        this.entities = entities;
        this.terrain = terrain;
        frame = new JFrame(Config.WINDOW_NAME);
        setFocusable(true);
        requestFocus();
        setMenu();

    }

    @Override
    public void update(Observable o, Object arg) {
        frame.repaint();
        requestFocus();
    }

    /**
     * Sets view as main menu.
     */
    public void setMenu() {
        frame.remove(gridPanel);
        frame.add(menuPanel);
        frame.pack();
        frame.setVisible(true);
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
        gridPanel.remove(escapeMenuPanel);
        frame.remove(gridPanel);
        gridPanel.remove(gridComponent);
        setGame();
    }

    /**
     * Sets view as game.
     */
    public void setGame() {
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        frame.remove(menuPanel);

        p.add(gridPanel,BorderLayout.CENTER);
        gridPanel.add(gridComponent,1);

        JPanel sidePane = new JPanel();
        sidePane.setLayout(new BoxLayout(sidePane,BoxLayout.Y_AXIS));

        sidePane.add(sideComponent);
        sidePane.add(sidePanel);

        p.add(sidePane, BorderLayout.EAST);
        frame.add(p);
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
}
