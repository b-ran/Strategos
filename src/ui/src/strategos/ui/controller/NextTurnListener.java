package strategos.ui.controller;

import strategos.model.SaveInstance;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static strategos.ui.config.Config.OTHER_PLAYER_NAME;

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

        if (model.getWinner() > 0) {
            JOptionPane.showMessageDialog(view.getGridComponent(), "Player " + model.getWinner() + " wins!\n" +
                    "Game took " + model.getNumberTurns() + " turns");
            return;
        }

        controller.setSelectedMapLocation(null);
        view.getGridComponent().setSelection(null);
        view.getSideComponent().setSelection(null, null);
        for (int i = 0; i < model.getPlayers().get(2).getUnits().size(); i++) {
            model.getPlayers().get(2).getUnits().get(i).turnTick();
        }
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
