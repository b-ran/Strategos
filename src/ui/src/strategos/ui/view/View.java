package strategos.ui.view;


import strategos.terrain.Terrain;
import strategos.ui.config.Config;
import strategos.units.Unit;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class View extends JComponent implements Observer {


    private JFrame frame; //Overall Frame

    protected List<Unit> entities;
    protected Terrain[][] terrain;

    private MenuComponent menuComponent = new MenuComponent();
    private MenuComponent escapeMenuComponent = new MenuComponent();
    private GridComponent gridComponent = new GridComponent();
    private SideComponent sideComponent = new SideComponent();

    private JPanel menuPanel = menuComponent.setMenu();
    private JPanel escapeMenuPanel = escapeMenuComponent.setEscapeMenu();
    private JLayeredPane gridPanel = gridComponent.getGrid();
    private JPanel sidePanel = sideComponent.getSidePanel();

    protected boolean game = false;

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

    public void setMenu() {
        frame.remove(gridPanel);
        frame.add(menuPanel);
        frame.pack();
        frame.setVisible(true);
        game = false;
    }

    public void addEscapeMenu() {
        gridPanel.add(escapeMenuPanel,0);
        repack();
    }

    public void removeEscapeMenu() {
        gridPanel.remove(escapeMenuPanel);
        frame.remove(gridPanel);
        gridPanel.remove(gridComponent);
        setGame();
    }

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

    public boolean status() {
        return game;
    }

    public void exit() {
        frame.dispose();
    }

    public MenuComponent getMenuComponent() {
        return menuComponent;
    }

    public GridComponent getGridComponent() {
        return gridComponent;
    }

    private void repack() {
        frame.pack();
        frame.setVisible(true);
        gridComponent.setFocusable(true);
        gridComponent.requestFocus();
    }

    public MenuComponent getEscapeMenuComponent() {
        return escapeMenuComponent;
    }
}
