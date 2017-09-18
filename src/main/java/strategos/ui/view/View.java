package strategos.ui.view;

import strategos.util.Entity;

import java.util.LinkedList;
import java.util.List;

public class View {

    List<Entity> entities = new LinkedList<>();
    List<Entity> terrain = new LinkedList<>();

    public View(List<Entity> entities, List<Entity> terrain) {
        this.entities = entities;
        this.terrain = terrain;
    }

    public View() {

    }
}
