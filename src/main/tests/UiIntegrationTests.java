import mapcreation.mapgeneration.TerrainGeneration;
import org.junit.Test;
import strategos.Paintable;
import strategos.behaviour.BehaviourFactoryImpl;
import strategos.hexgrid.Map;
import strategos.mapGenerator.Generator;
import strategos.model.*;
import strategos.networking.NetworkingHandler;
import strategos.networking.handlers.NetworkingHandlerImpl;
import strategos.ui.Ui;
import strategos.units.Unit;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static strategos.Config.MAP_DIAMETER;
import static strategos.Direction.WEST;

/**
 * Tests ui with map generation, model and networking
 *
 * @author Brandon Scott-Hill
 */
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

    private void waitToClose(Ui ui, int mill) {
        try {
            Thread.sleep(mill);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ui.exit();

    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font(Font.SANS_SERIF,1,16));
        g.drawString(text,50,50);
    }

    /**
     * Tests that if map is generated and displayed
     */
    @Test
    public void uiAndMapCreationTest_1() {
        setText("You should able to see a game board with a generated map");
        StateCreator stateCreator = new StateCreator(new BehaviourFactoryImpl(), new TerrainGeneration());
        Ui ui = new Ui(stateCreator.createNewState(), new NetworkingHandlerImpl());
        ui.skipMenu();
        ui.disableInput();
        ui.revealMap();
        waitToClose(ui,10000);
        frame.dispose();
    }

    /**
     * Tests that if a unique map is generated and displayed every time
     */
    @Test
    public void uiAndMapCreationTest_2() {
        setText("You should able to see a game board with a unique generated map every time");
        for (int i = 0; i < 20; i++) {
            StateCreator stateCreator = new StateCreator(new BehaviourFactoryImpl(), new TerrainGeneration());
            Ui ui = setupUI(stateCreator.createNewState(), new NetworkingHandlerImpl());
            waitToClose(ui,1000);
        }
        frame.dispose();
    }

    /**
     * Tests that if a unique map is generated and displayed every time
     */
    @Test
    public void uiAndMapCreationTest_3() {
        setText("The generated map should look the same every time");
        for (int i = 0; i < 20; i++) {
            Map map = new Map(MAP_DIAMETER);
            Generator generator = new TerrainGeneration();
            Paintable[][] terrainMap = generator.populateMap(map.getData(),2146747743);

            for (int x = 0; x < terrainMap.length; x++) {
                for (int y = 0; y < terrainMap.length; y++) {
                    map.getData()[x][y].setTerrain(terrainMap[x][y].getTerrain());
                }
            }
            StateCreator stateCreator = new StateCreator(new BehaviourFactoryImpl(), generator);
            UnitOwner playerOne = new Player(false);
            UnitOwner playerTwo = new Player(false);
            UnitOwner barbarians = new Player(true);
            GameState newState = new Strategos(stateCreator, new World(map, new ArrayList<>()), playerOne, playerTwo, barbarians);

            Ui ui = setupUI(newState, new NetworkingHandlerImpl());
            waitToClose(ui,1000);
        }
        frame.dispose();
    }

    /**
     * Tests barbarians moving is updated on ui
     */
    @Test
    public void uiAndModelTest_1() {
        setText("You should able to see barbarians move once");
        StateCreator stateCreator = new StateCreator(new BehaviourFactoryImpl(), new TerrainGeneration());
        GameState gameState = stateCreator.createNewState();
        Ui ui = setupUI(gameState, new NetworkingHandlerImpl());

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gameState.nextTurn();
        gameState.nextTurn();
        gameState.notifyObservers(null);
        waitToClose(ui,3000);
        frame.dispose();
    }

    /**
     * Tests barbarians moving & attacking is updated on ui
     */
    @Test
    public void uiAndModelTest_2() {
        setText("You should able to see barbarians swarming");
        StateCreator stateCreator = new StateCreator(new BehaviourFactoryImpl(), new TerrainGeneration());
        GameState gameState = stateCreator.createNewState();
        Ui ui = setupUI(gameState, new NetworkingHandlerImpl());

        for (int i = 0; i < 20; i++) {
            gameState.nextTurn();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            gameState.notifyObservers(null);
        }
        waitToClose(ui,3000);
        frame.dispose();
    }

    /**
     * Tests moving unit 3 spaces and updating on the ui
     */
    @Test
    public void uiAndModelTest_3() {
        setText("You should able to see a unit move");
        StateCreator stateCreator = new StateCreator(new BehaviourFactoryImpl(), new TerrainGeneration());
        GameState gameState = stateCreator.createNewState();
        Unit unit = gameState.getPlayers().get(0).getUnits().get(0);
        unit.turnTick();

        Ui ui = setupUI(gameState, new NetworkingHandlerImpl());

        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            gameState.move(unit, WEST);
            gameState.notifyObservers(null);
        }

        waitToClose(ui,1000);
        frame.dispose();
    }

    /**
     * Tests server moving a unit then sending update to client and that update draws on the ui
     */
    @Test
    public void uiAndNetworkTest_1() throws InterruptedException {
        setText("Should see the bottom left unit one hex to the right of spearman (may take a bit to open)");
        StateCreator stateCreator = new StateCreator(new BehaviourFactoryImpl(), new TerrainGeneration());

        GameState serverState = stateCreator.createNewState();
        serverState.setThisInstancePlayer(serverState.getPlayers().get(0));

        GameState clientState = stateCreator.createNewState();
        clientState.setThisInstancePlayer(clientState.getPlayers().get(1));

        IntegrationTests integrationTests = new IntegrationTests();
        NetworkingHandler server = integrationTests.setupHandler(false, serverState);
        NetworkingHandler client = integrationTests.setupHandler(true, clientState);

        SaveInstance saveInstance = serverState.export();
        server.send(saveInstance);

        Ui ui = setupUI(clientState, client);


        Unit unit = serverState.getPlayers().get(0).getUnits().get(0);
        MapLocation location = unit.getPosition();
        unit.turnTick();
        serverState.move(unit, location.getNeighbour(WEST));

        saveInstance = serverState.export();
        server.send(saveInstance);

        ui.revealMap();
        clientState.notifyObservers(null);

        waitToClose(ui,10000);
        frame.dispose();
        server.stop();
        client.stop();
    }


    private Ui setupUI(GameState gameState, NetworkingHandler networkingHandler) {
        Ui ui = new Ui(gameState, networkingHandler);
        ui.skipMenu();
        ui.disableInput();
        ui.revealMap();
        return ui;
    }
}
