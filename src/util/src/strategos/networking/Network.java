package strategos.networking;

import strategos.SaveInstance;

/**
 * Interface for Servers and Client
 */
public interface Network {
	/**
	 * Creates the Network
	 * @throws InterruptedException if the connection is interrupted
	 */
	void run() throws InterruptedException;

	/**
	 * Sends a save state through the Network
	 * @param instance The state to send
	 * @throws InterruptedException if the connection is interrupted
	 */
	void send(SaveInstance instance) throws InterruptedException;

	/**
	 * Gets called when a SaveInstance is received
	 * @param instance The received instance
	 */
	void receive(SaveInstance instance);
}