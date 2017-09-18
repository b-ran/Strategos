package strategos.ui.view;

import strategos.util.Entity;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class View extends JComponent implements Observer {

    protected List<Entity> entities = new LinkedList<>();
    protected List<Entity> terrain = new LinkedList<>();

    public View(List<Entity> entities, List<Entity> terrain) {
        this.entities = entities;
        this.terrain = terrain;
    }

    public View() {
    }

    @Override
    public void update(Observable o, Object arg) {
    }
}
