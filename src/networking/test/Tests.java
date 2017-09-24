import networking.NetworkingHandler;
import org.junit.Test;

public class Tests {
	@Test
	public void testServer() {
		NetworkingHandler handler = new NetworkingHandler();
		handler.initialise(8080);
	}
}
