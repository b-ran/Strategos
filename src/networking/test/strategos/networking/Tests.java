package strategos.networking;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import strategos.GameState;
import strategos.SaveInstance;
import strategos.networking.handlers.NetworkingHandlerImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Tests {
	private SaveInstance testSaveInstance;
	private TestGameState serverState;
	private TestGameState clientState;
	private NetworkingHandler server;
	private NetworkingHandler client;

	@BeforeEach
	void setupNetworks() throws InterruptedException {
		testSaveInstance = new TestSaveInstance("This is for testing purposes", 15);
		serverState = new TestGameState();
		clientState = new TestGameState();
		server = setupHandler(false, serverState);
		client = setupHandler(true, clientState);
	}

	@AfterEach
	void stopNetworks() throws InterruptedException {
		server.stop();
		client.stop();
	}

	@Test
	void testSendFromServer() throws InterruptedException {
		server.send(testSaveInstance);
		assertEquals(testSaveInstance, clientState.instance);
	}

	@Test
	void testReceiveFromServer() throws InterruptedException {
		client.send(testSaveInstance);
		assertEquals(testSaveInstance, serverState.instance);
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