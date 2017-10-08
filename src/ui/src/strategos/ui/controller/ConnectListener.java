package strategos.ui.controller;

import strategos.networking.NetworkingHandler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ConnectListener extends Controller implements ActionListener{

    private NetworkingHandler networkingHandler;

    ConnectListener(Controller controller, NetworkingHandler networkingHandler) {
        super(controller);
        this.networkingHandler = networkingHandler;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        model.setThisInstancePlayer(model.getPlayers().get(1));
        view.setSeenTerrain(model.getThisInstancePlayer().getVisibleTiles());
        networkingHandler.initialise(model, "", 5000);
    }
}
