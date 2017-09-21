package strategos.ui;

import strategos.hexgrid.Hex;
import strategos.model.units.Unit;
import strategos.ui.controller.Controller;
import strategos.ui.view.View;

import java.util.ArrayList;
import java.util.List;

public class Ui {

    public Ui(List<Unit> entities, Hex[][] terrain) {
        View view = new View(entities, terrain);
        Controller controller = new Controller(entities, terrain, view);
    }


    public static void main(String[] args) {
        View view = new View(new ArrayList<>(), null);
        Controller controller = new Controller(null, null, view);
    }
}
