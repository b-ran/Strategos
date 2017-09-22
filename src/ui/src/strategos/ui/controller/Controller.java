package strategos.ui.controller;

import strategos.Graphical;
import strategos.ui.view.View;

import java.util.List;

public class Controller {

    protected List<Graphical> entities;
    protected Graphical[][]         terrain;
    protected View view = new View(entities, terrain);

    public Controller(List<Graphical> entities, Graphical[][] terrain, View view) {
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
