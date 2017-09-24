import strategos.networking.NetworkHandlerImpl;
import org.junit.Test;

public class Tests {
	@Test
	public void testServer() throws InterruptedException {
		NetworkHandlerImpl handler = new NetworkHandlerImpl();
		handler.initialise(8080);
		handler.run();
	}
}
