package strategos.ui.controller;

import strategos.ui.view.View;
import strategos.util.Entity;

import java.util.LinkedList;
import java.util.List;

public class Controller {

    List<Entity> entities = new LinkedList<>();
    List<Entity> terrain = new LinkedList<>();
    View view = new View();

    public Controller(List<Entity> entities, List<Entity> terrain, View view) {
        this.entities = entities;
        this.terrain = terrain;
        this.view = view;
    }
}
