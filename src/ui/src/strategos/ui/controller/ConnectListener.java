package strategos.ui.controller;

import strategos.networking.NetworkingHandler;

import javax.swing.*;
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
        String s = (String) JOptionPane.showInputDialog(
                view.getMenuComponent(),
                "Host IP",
                "Connect",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "");
        if (s != null) {
            networkingHandler.initialise(model, s, 8080);
            try {
                System.out.println("client running");
                networkingHandler.run();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            view.setSeenTerrain(model.getThisInstancePlayer().getVisibleTiles());
            view.setGame();
            view.getGridComponent().setEntities(model.getWorld().getAllUnits());
            view.getGridComponent().setTerrain(model.getWorld().getMap().getData());
        }
    }
}
