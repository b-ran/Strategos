package strategos.networking;

import strategos.GameState;
import strategos.SaveInstance;

/**
 * Handles everything to do with 
 */
public interface NetworkingHandler {

	/**
	 * Initialises the handler as a server
	 * @param port The port to open the server on
	 */
	void initialise(GameState state, int port);

	/**
	 * Initialises the handler as a client
	 * @param host The IP of the server
	 * @param port The port the server was opened on
	 */
	void initialise(GameState state, String host, int port);

	/**
	 * Runs the handler
	 */
	void run() throws InterruptedException;

	/**
	 * Sends a {@code SaveInstance} over the network
	 * @param instance The SaveInstance to be sent
	 * @throws InterruptedException when the connection is interrupted
	 */
	void send(SaveInstance instance) throws InterruptedException;
}
