package strategos.ui.controller;

import strategos.UnitOwner;

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
        model.nextTurn();

        UnitOwner unitOwner = model.getCurrentTurn();

        if (unitOwner != uiOwner) {
            view.getSideComponent().setPlayerText(OTHER_PLAYER_NAME);
        } else {
            view.getSideComponent().setPlayerText(PLAYER_NAME);
        }
        controller.setSelectedMapLocation(null);
        view.getGridComponent().setSelection(null);
        view.getSideComponent().setSelection(null, null);
        view.repaint();
        try {
            System.out.println("sending L1");
            controller.getNetworkingHandler().send(model.export());
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }
}
