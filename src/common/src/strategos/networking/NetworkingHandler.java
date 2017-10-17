package strategos.networking;

import strategos.model.GameState;
import strategos.model.SaveInstance;

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
	 * Sends a {@link SaveInstance} over the network
	 * @param instance The SaveInstance to be sent
	 * @throws InterruptedException when the connection is interrupted
	 */
	void send(SaveInstance instance) throws InterruptedException;

	/**
	 * Shuts down the connection
	 */
	void stop() throws InterruptedException;

	/**
	 * Closes the connections, and resets the handler. <br>
	 * {@link #initialise} must be called after this method to use the handler again
	 */
	void reset() throws InterruptedException;

	/**
	 * Is the network connected
	 * @return true if the network has an ongoing connection
	 */
	boolean isConnected();

	/**
	 * Set the connection status of the network
	 * @param connected the connection status to be set
	 */
	void setConnected(boolean connected);
}
