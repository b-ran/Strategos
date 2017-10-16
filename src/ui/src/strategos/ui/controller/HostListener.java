package strategos.ui.controller;

import strategos.networking.NetworkingHandler;
import strategos.units.Unit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The Host listener.
 *
 * @author Daniel Pinfold
 */
public class HostListener extends Controller implements ActionListener {

    private NetworkingHandler networkingHandler;

    /**
     * Instantiates a new Host listener.
     *
     * @author Daniel Pinfold
     *
     * @param controller        the controller
     * @param networkingHandler the networking handler
     */
    HostListener(Controller controller, NetworkingHandler networkingHandler) {
        super(controller);
        this.networkingHandler = networkingHandler;
    }

    @Override
    public void actionPerformed(ActionEvent e) {


        JOptionPane msg = new JOptionPane("Waiting for Client");
        final JDialog dlg = msg.createDialog("Hosting");
        new Thread(()-> {
            dlg.setVisible(true);
        }).start();
        System.out.println("ho");
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
            view.setFirstTurn(false);
            view.setSeenTerrain(model.getThisInstancePlayer().getVisibleTiles());
            view.setGame();
            view.getGridComponent().setEntities(model.getWorld().getAllUnits());
            view.getGridComponent().setTerrain(model.getWorld().getMap().getData());
            dlg.setVisible(false);
        }).start();

    }
}
