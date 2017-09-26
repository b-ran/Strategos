package strategos.networking;

import org.junit.Test;
import strategos.SaveInstance;
import strategos.networking.NetworkingHandler;
import strategos.networking.handlers.NetworkingHandlerImpl;

public class Tests {
	@Test
	public void testSendFromServer() throws InterruptedException {
/*
		NetworkingHandler server = new NetworkingHandlerImpl();
		NetworkingHandler client = new NetworkingHandlerImpl();
		server.initialise(8080);
		client.initialise("localhost", 8080);
		new Thread(() -> {
			try {
				server.run();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).run();
		new Thread(() -> {
			try {
				client.run();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		try {
			SaveInstance instance = SaveInstance.class.newInstance();
			server.send(instance);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
*/

	}
}
