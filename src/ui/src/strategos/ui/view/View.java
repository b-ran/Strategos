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

    public View(List<Unit> entities, Terrain[][] terrain) {
        this.entities = entities;
        this.terrain = terrain;
        frame = new JFrame(Config.WINDOW_NAME);
        setMenu();
        frame.pack();
        frame.setVisible(true);
    }


    @Override
    public void update(Observable o, Object arg) {
        frame.repaint();
        requestFocus();
    }

    public void setMenu() {
        frame.dispose();
        frame.add(new MenuComponent().getMenu());
    }

    public void setEscapeMenu() {
        frame.dispose();
        frame.add(new MenuComponent().getEscapeMenu());
    }
}
