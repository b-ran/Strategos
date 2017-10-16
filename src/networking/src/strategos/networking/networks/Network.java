package strategos.networking.networks;

import strategos.model.SaveInstance;

/**
 * Interface for Servers and Client
 */
public interface Network {
	/**
	 * Creates the Network
	 */
	void run();

	/**
	 * Sends a save state through the Network
	 *
	 * @param instance The state to send
	 */
	void send(SaveInstance instance);

	/**
	 * Gets called when a SaveInstance is received
	 *
	 * @param instance The received ins	tance
	 */
	void receive(SaveInstance instance);

	/**
	 * Shuts down the connection
	 */
	void stop();

	/**
	 * Sets the status of the server to {@code connected}
	 * @param connected The new status of the server
	 */

	void setConnected(boolean connected);
}