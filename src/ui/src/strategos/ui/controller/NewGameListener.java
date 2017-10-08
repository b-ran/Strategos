package strategos.ui.controller;

import strategos.units.Unit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class NewGameListener extends Controller implements ActionListener {

    Controller controller;

    NewGameListener(Controller controller) {
        super(controller);
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!controller.allInput) return;
        for (Unit u : model.getPlayers().get(0).getUnits()) {
            u.turnTick();
        }
        view.setGame();
        view.getGridComponent().setEntities(model.getWorld().getAllUnits());
        view.getGridComponent().setTerrain(model.getWorld().getMap().getData());
    }
}
