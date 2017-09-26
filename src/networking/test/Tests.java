import org.junit.Test;
import strategos.networking.handlers.NetworkHandlerImpl;
import strategos.networking.implementations.SaveInstanceImpl;

public class Tests {
	@Test
	public void testConnection() throws InterruptedException {
		NetworkHandlerImpl server = new NetworkHandlerImpl();
		NetworkHandlerImpl client = new NetworkHandlerImpl();
		server.initialise(8080);
		client.initialise("localhost", 8080);
		server.run();
		client.run();
		server.send(new SaveInstanceImpl());
	}
}
