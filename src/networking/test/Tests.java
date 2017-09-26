import org.junit.Test;
import strategos.networking.NetworkHandler;
import strategos.networking.handlers.NetworkHandlerImpl;

public class Tests {
	@Test
	public void testSendFromServer() throws InterruptedException {
		NetworkHandler server = new NetworkHandlerImpl();
		NetworkHandler client = new NetworkHandlerImpl();
		server.initialise(8080);
		client.initialise("localhost", 8080);
		server.run();
		client.run();
		server.send();
	}
}
