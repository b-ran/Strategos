package strategos.networking.external_testing;

import org.junit.Test;
import strategos.*;
import strategos.networking.NetworkingHandler;
import strategos.networking.TestGameState;
import strategos.networking.TestSaveInstance;
import strategos.networking.handlers.NetworkingHandlerImpl;
import strategos.units.Unit;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ExternalTests {
	private SaveInstance testSave;
	private GameState serverState;
	private GameState clientState;
	private NetworkingHandler server;
	private NetworkingHandler client;

	void setupNetworks() throws InterruptedException {
		GameBoard testMap = new ExternalTestMap(createMap(false,10), 10);
		List<Unit> units = new ArrayList<>();
		units.add(new ExternalTestUnit(1000));
		GameCollections testWorld = new ExternalTestWorld(testMap, units);

		serverState = new ExternalTestGameState(testWorld, new ArrayList<>());

		testMap = new ExternalTestMap(createMap(true,10), 10);
		units = new ArrayList<>();
		units.add(new ExternalTestUnit(500));
		testWorld = new ExternalTestWorld(testMap, units);

		clientState = new ExternalTestGameState(testWorld, new ArrayList<>());
		server = setupHandler(false, serverState);
		client = setupHandler(true, clientState);
	}

	void stopNetworks() throws InterruptedException {
		server.stop();
		client.stop();
	}

	@Test
	public void SendTest_01() throws InterruptedException, java.io.EOFException {
		setupNetworks();

		SaveInstance saveInstance = serverState.export();

		server.send(saveInstance);
		assertTrue(clientState.getWorld().getAllUnits().get(0).getStrength() == 1000);

		stopNetworks();
	}

	@Test
	public void SendTest_03() throws InterruptedException {
		setupNetworks();

		SaveInstance saveInstance = serverState.export();

		server.send(saveInstance);

		for (int x = 0; x < serverState.getWorld().getMap().getData().length; x++) {
			for (int y = 0; y < serverState.getWorld().getMap().getData().length; y++) {
				assertTrue(serverState.getWorld().getMap().getData()[x][y].getX() == clientState.getWorld().getMap().getData()[x][y].getX());
			}
		}

		server.stop();

		stopNetworks();
	}

	@Test
	public void SendTest_02() throws InterruptedException {
		setupNetworks();

		SaveInstance saveInstance = serverState.export();

		server.stop();

		server.send(saveInstance);

		stopNetworks();
	}

	/**
	 *
	 * @param wrong for testing if the maps are different types.
	 * @param diameter
	 * @return
	 */
	private MapLocation[][] createMap(boolean wrong, int diameter) {
		MapLocation[][] data = new MapLocation[diameter][diameter];
		for (int x = 0; x < diameter; x++) {
			for (int y = 0; y < diameter; y++) {
				if (wrong) {
					data[x][y] = new ExternalTestLocation(x + 1, y);
				} else {
					data[x][y] = new ExternalTestLocation(x, y);
				}
			}
		}
		return data;
	}

	private NetworkingHandler setupHandler(boolean client, GameState state) throws InterruptedException {
		NetworkingHandler handler = new NetworkingHandlerImpl();
		if (client) {
			handler.initialise(state, 8080);
		} else {
			handler.initialise(state, "127.0.0.1", 8080);
		}
		handler.run();
		return handler;
	}

}
