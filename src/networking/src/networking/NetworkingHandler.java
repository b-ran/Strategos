package networking;

import networking.client.Client;
import networking.server.Server;

public class NetworkingHandler {

	private Network type;

	/**
	 * Initialises the handler as a server
	 * @param port The port to open the server on
	 */
	public void initialise(int port) {
		type = new Server(port);
	}

	/**
	 * Initialises the handler as a client
	 * @param host The IP of the server
	 * @param port The port the server was opened on
	 */
	public void initialise(String host, int port) {
		type = new Client(host, port);
	}

	/**
	 * Runs the handler
	 * @throws InterruptedException when the connection is unexpectedly interrupted
	 */
	public void run() throws InterruptedException {
		type.run();
	}
}