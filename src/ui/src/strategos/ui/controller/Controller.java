package strategos.ui.controller;

import strategos.terrain.Terrain;
import strategos.ui.view.*;
import strategos.ui.view.MenuComponent;
import strategos.units.Unit;

import java.awt.*;
import java.util.List;

public class Controller {

    protected List<Unit> entities;
    protected Terrain[][] terrain;
    protected View view;
    private boolean start = true;

    public Controller(List<Unit> entities, Terrain[][] terrain, View view) {
        this.entities = entities;
        this.terrain = terrain;
        this.view = view;
        setMenuListeners();
    }

    protected Controller(Controller controller) {
        this.entities = controller.entities;
        this.terrain = controller.terrain;
        this.view = controller.view;
    }

    protected void setMenuListeners() {
        MenuComponent m = view.getMenuComponent();
        if (view.status() == false) {
            m.getNewGameButton().addActionListener(new NewGameListener(this));
            m.getLoadButton().addActionListener(new LoadListener(this));
            m.getConnectButton().addActionListener(new ConnectListener(this));
            m.getHostButton().addActionListener(new HostListener(this));
            m.getExitButton().addActionListener(new ExitListener(this));
        } else {
            m.getResumeButton().addActionListener(new ResumeListener(this));
            m.getNewGameButton().addActionListener(new NewGameListener(this));
            m.getSaveButton().addActionListener(new SaveListener(this));
            m.getLoadButton().addActionListener(new LoadListener(this));
            m.getExitButton().addActionListener(new ExitListener(this));
        }
    }
}
