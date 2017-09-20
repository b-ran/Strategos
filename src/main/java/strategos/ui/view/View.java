package strategos.ui.view;

import strategos.hexgrid.Hex;
import strategos.model.units.Unit;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

public class View extends JComponent implements Observer {

    protected Unit[][] entities;
    protected Hex[][] terrain;

    public View(Unit[][] entities, Hex[][] terrain) {
        this.entities = entities;
        this.terrain = terrain;
    }

    public View() {
    }

    @Override
    public void update(Observable o, Object arg) {
    }
}
