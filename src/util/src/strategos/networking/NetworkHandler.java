package strategos.networking;

/**
 * Handles everything to do with 
 */
public interface NetworkHandler {

	/**
	 * Initialises the handler as a server
	 * @param port The port to open the server on
	 */
	void initialise(int port);

	/**
	 * Initialises the handler as a client
	 * @param host The IP of the server
	 * @param port The port the server was opened on
	 */
	void initialise(String host, int port);

	/**
	 * Runs the handler
	 * @throws InterruptedException when the connection is unexpectedly interrupted
	 */
	void run() throws InterruptedException;

	/**
	 * Gets the type of network
	 * @return The Network the
	 */

	Network getType();
}
