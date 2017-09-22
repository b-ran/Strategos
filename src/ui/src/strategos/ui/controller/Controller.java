package strategos.ui.controller;

import strategos.terrain.Terrain;
import strategos.ui.view.View;
import strategos.units.Unit;

import java.util.List;

public class Controller {

    protected List<Unit> entities;
    protected Terrain[][] terrain;
    protected View view = new View(entities, terrain);

    public Controller(List<Unit> entities, Terrain[][] terrain, View view) {
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
