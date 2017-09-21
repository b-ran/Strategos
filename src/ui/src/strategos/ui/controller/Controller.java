package strategos.ui.controller;

import strategos.hexgrid.Hex;
import strategos.model.units.Unit;
import strategos.ui.view.View;

import java.util.List;

public class Controller {

    protected List<Unit> entities;
    protected Hex[][] terrain;
    protected View view = new View(entities, terrain);

    public Controller(List<Unit> entities, Hex[][] terrain, View view) {
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
