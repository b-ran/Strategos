package strategos.ui.controller;

import strategos.networking.NetworkingHandler;
import strategos.ui.view.NetworkTestSaveInstance;
import sun.instrument.InstrumentationImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.instrument.Instrumentation;
import java.util.Arrays;

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
            try {
                System.out.println("initial send L1");
                networkingHandler.send(model.export());
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            System.out.println("ended t/c");
            view.setSeenTerrain(model.getThisInstancePlayer().getVisibleTiles());
            view.setGame();
            view.getGridComponent().setEntities(model.getWorld().getAllUnits());
            view.getGridComponent().setTerrain(model.getWorld().getMap().getData());
        }).start();

    }
}
