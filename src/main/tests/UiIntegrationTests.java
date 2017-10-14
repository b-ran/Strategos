import mapcreation.mapgeneration.TerrainGeneration;
import org.junit.Test;
import strategos.behaviour.BehaviourFactoryImpl;
import strategos.model.GameState;
import strategos.model.MapLocation;
import strategos.model.StateCreator;
import strategos.networking.handlers.NetworkingHandlerImpl;
import strategos.ui.Ui;
import strategos.units.Unit;

import javax.swing.*;
import java.awt.*;

public class UiIntegrationTests extends JComponent{


    private JFrame frame = new JFrame();
    private String text = "";
    private Ui ui;

    private int screenHeight = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight();


    public UiIntegrationTests() {
        frame.add(this);
        frame.setTitle("Debug View");
        frame.setPreferredSize(new Dimension(800,200));
        frame.setLocation(0, screenHeight-300);
        frame.pack();
        frame.setVisible(true);
    }

    private void setText(String correctOutput) {
        text = correctOutput;
        frame.repaint();
    }

    private void waitToClose(int mill) {
        new Thread(() -> {
            try {
                Thread.sleep(mill);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ui.exit();
            frame.dispose();
        }).start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font(Font.SANS_SERIF,1,16));
        g.drawString(text,50,50);
    }

    @Test
    public void uiAndMapCreationTest_1() {
        setText("You should able to see a game board with a generated map");
        StateCreator stateCreator = new StateCreator(new BehaviourFactoryImpl(), new TerrainGeneration());
        ui = new Ui(stateCreator.createNewState(), new NetworkingHandlerImpl());
        ui.skipMenu();
        ui.disableInput();
        ui.revealMap();
        waitToClose(3000);
    }

    @Test
    public void uiAndModelTest_2() {
        setText("You should able to see barbarians swarming");
        StateCreator stateCreator = new StateCreator(new BehaviourFactoryImpl(), new TerrainGeneration());
        GameState gameState = stateCreator.createNewState();
        ui = new Ui(gameState, new NetworkingHandlerImpl());
        ui.skipMenu();
        ui.disableInput();
        ui.revealMap();
        waitToClose(80000);
        for (int i = 0; i < 200; i++) {
            gameState.nextTurn();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            gameState.notifyObservers(null);
        }
    }

    

}
