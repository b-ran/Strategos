package strategos.ui;

import strategos.ui.controller.Controller;
import strategos.ui.view.View;
import strategos.units.*;
import strategos.terrain.*;

import java.util.ArrayList;
import java.util.List;


/**
 * The type Ui.
 */
public class Ui {

    /**
     * Instantiates a new Ui.
     *
     * @param entities the units on the board
     * @param terrain  the terrain that makes up board
     */
    public Ui(List<Unit> entities, Terrain[][] terrain) {
        View view = new View(entities, terrain);
        Controller controller = new Controller(entities, terrain, view);
    }


    /**
     * The entry point of application for self running.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        View view = new View(new ArrayList<>(), null);
        Controller controller = new Controller(null, null, view);
    }
}
