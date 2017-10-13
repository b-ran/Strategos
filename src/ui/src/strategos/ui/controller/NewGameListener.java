package strategos.ui.controller;

import strategos.model.SaveInstance;
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
        model = model.newGame();
        model.notifyObservers(null);

        model.setThisInstancePlayer(model.getPlayers().get(0));
        view.setSeenTerrain(model.getThisInstancePlayer().getVisibleTiles());
        for (Unit u : model.getWorld().getAllUnits()) {
            u.turnTick();
        }
        view.setFirstTurn(false);
        view.setSeenTerrain(model.getThisInstancePlayer().getVisibleTiles());
        view.setGame();
        view.getGridComponent().setEntities(model.getWorld().getAllUnits());
        view.getGridComponent().setTerrain(model.getWorld().getMap().getData());
        view.repaint();
    }
}
