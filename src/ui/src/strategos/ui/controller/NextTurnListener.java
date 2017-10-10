package strategos.ui.controller;

import strategos.GameState;
import strategos.SaveInstance;
import strategos.UnitOwner;
import strategos.units.Unit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static strategos.ui.config.Config.OTHER_PLAYER_NAME;
import static strategos.ui.config.Config.PLAYER_NAME;

class NextTurnListener extends Controller implements ActionListener {

    private Controller controller;

    NextTurnListener(Controller controller) {
        super(controller);
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (model.getPlayers().indexOf(model.getThisInstancePlayer()) != model.getPlayers().indexOf(model.getCurrentTurn())) {
            return;
        }
        model.nextTurn();

        UnitOwner unitOwner = model.getCurrentTurn();

        if (unitOwner != uiOwner) {
            view.getSideComponent().setPlayerText(OTHER_PLAYER_NAME);
        } else {
            view.getSideComponent().setPlayerText(PLAYER_NAME);
        }
        controller.setSelectedMapLocation(null);
        view.getGridComponent().setSelection(null);
        view.getSideComponent().setSelection(null, null); //TODO: review
        if (model.getPlayers().indexOf(unitOwner) == 1) {
            for (Unit barbarian : model.getPlayers().get(2).getUnits()) {
                barbarian.turnTick();
            }
        }
        view.repaint();
        SaveInstance exported = model.export();
        if (exported.getPlayers().indexOf(exported.getTurn()) == model.getPlayers().indexOf(uiOwner)) {
            view.setUiOwner(exported.getTurn());
        }
        try {
            controller.getNetworkingHandler().send(exported);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }
}
