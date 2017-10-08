package strategos.ui.controller;

import strategos.networking.NetworkingHandler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HostListener extends Controller implements ActionListener {

    private NetworkingHandler networkingHandler;

    HostListener(Controller controller, NetworkingHandler networkingHandler) {
        super(controller);
        this.networkingHandler = networkingHandler;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        model.setThisInstancePlayer(model.getPlayers().get(0));
        view.setSeenTerrain(model.getThisInstancePlayer().getVisibleTiles());
        networkingHandler.initialise(model, 5000);
    }
}
