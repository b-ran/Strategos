package strategos.ui;

import strategos.hexgrid.Hex;
import strategos.model.units.Unit;
import strategos.ui.controller.Controller;
import strategos.ui.view.View;

public class Ui {

    public Ui(Unit[][] entities, Hex[][] terrain) {
        View view = new View(entities, terrain);
        Controller controller = new Controller(entities, terrain, view);
    }


    public static void main(String[] args) {
        View view = new View(null, null);
        Controller controller = new Controller(null, null, view);
    }
}
