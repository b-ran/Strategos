package strategos.ui.view;


import strategos.terrain.Terrain;
import strategos.ui.config.Config;
import strategos.units.Unit;

import javax.swing.*;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class View extends JComponent implements Observer {


    private JFrame frame; //Overall Frame

    protected List<Unit> entities;
    protected Terrain[][] terrain;

    private MenuComponent menuComponent = new MenuComponent();
    private GridComponent gridComponent = new GridComponent();

    private JPanel menuPanel = menuComponent.getMenu();
    private JPanel gridPanel = gridComponent.getGrid();

    protected boolean game = false;

    public View(List<Unit> entities, Terrain[][] terrain) {
        this.entities = entities;
        this.terrain = terrain;
        frame = new JFrame(Config.WINDOW_NAME);
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

    public void setEscapeMenu() {
        frame.add(menuComponent.getEscapeMenu());
    }

    public void setGame() {
        frame.remove(menuPanel);
        frame.add(gridPanel);
        frame.pack();
        frame.setVisible(true);
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
}
