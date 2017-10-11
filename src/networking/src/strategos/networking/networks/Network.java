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
	 * @param instance The received instance
	 */
	void receive(SaveInstance instance);

	/**
	 * Shuts down the connection
	 */
	void stop();

	void setConnected(boolean connected);
}