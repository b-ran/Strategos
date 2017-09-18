package strategos.ui;

import strategos.ui.controller.Controller;
import strategos.ui.view.View;
import strategos.util.Entity;

import java.util.List;

public class Ui {

    public Ui(List<Entity> entities, List<Entity> terrain) {
        View view = new View(entities, terrain);
        Controller controller = new Controller(entities, terrain, view);
    }

}
