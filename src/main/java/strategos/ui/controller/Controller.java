package strategos.ui.controller;

import strategos.ui.view.View;
import strategos.util.Entity;

import java.util.LinkedList;
import java.util.List;

public class Controller {

    protected List<Entity> entities = new LinkedList<>();
    protected List<Entity> terrain = new LinkedList<>();
    protected View view = new View();

    public Controller(List<Entity> entities, List<Entity> terrain, View view) {
        this.entities = entities;
        this.terrain = terrain;
        this.view = view;
    }

    protected Controller(Controller controller) {
        this.entities = controller.entities;
        this.terrain = controller.terrain;
        this.view = controller.view;
    }
}
