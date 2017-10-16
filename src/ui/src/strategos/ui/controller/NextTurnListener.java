package strategos.ui.controller;

import strategos.model.SaveInstance;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static strategos.ui.config.Config.OTHER_PLAYER_NAME;

/**
 * The Next turn listener for next turn button.
 *
 * @author Brandon Scott-Hill
 * @author Daniel Pinfold
 */
class NextTurnListener extends Controller implements ActionListener {

    private Controller controller;

    /**
     * Instantiates a new Next turn listener.
     *
     * @author Daniel Pinfold
     * @author Brandon Scott-Hill
     * @param controller the controller
     */
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

        if (model.getWinner() > 0) {
            JOptionPane.showMessageDialog(view.getGridComponent(), "Player " + model.getWinner() + " wins!\n" +
                    "Game took " + model.getNumberTurns() + " turns");
        }

        controller.setSelectedMapLocation(null);
        view.getGridComponent().setSelection(null);
        view.getSideComponent().setSelection(null, null);
        view.repaint();
        SaveInstance exported = model.export();
        try {
            controller.getNetworkingHandler().send(exported);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        view.getSideComponent().setPlayerText(OTHER_PLAYER_NAME);
    }
}
