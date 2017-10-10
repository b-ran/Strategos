package strategos.ui.controller;

import strategos.networking.NetworkingHandler;
import strategos.units.Unit;

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
        new Thread(()->{
            model.setThisInstancePlayer(model.getPlayers().get(0));
            view.setSeenTerrain(model.getThisInstancePlayer().getVisibleTiles());
            networkingHandler.initialise(model, 8080);
            try {
                networkingHandler.run();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            while (!networkingHandler.isConnected()) {
            	//TODO Show waiting for connection
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
            for (Unit u : model.getWorld().getAllUnits()) {
                u.turnTick();
            }
            try {
                networkingHandler.send(model.export());
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            view.setUiOwner(model.getThisInstancePlayer());
            view.setSeenTerrain(model.getThisInstancePlayer().getVisibleTiles());
            view.setGame();
            view.getGridComponent().setEntities(model.getWorld().getAllUnits());
            view.getGridComponent().setTerrain(model.getWorld().getMap().getData());
        }).start();

    }
}
