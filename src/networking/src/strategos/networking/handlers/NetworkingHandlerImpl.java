package strategos.networking.handlers;

import strategos.model.GameState;
import strategos.model.SaveInstance;
import strategos.networking.networks.Network;
import strategos.networking.NetworkingHandler;
import strategos.networking.TestGameState;
import strategos.networking.TestSaveInstance;
import strategos.networking.networks.Client;
import strategos.networking.networks.Server;

import java.util.Scanner;

/**
 * Handles initialising, running, and sending data from a client or server.
 */
public class NetworkingHandlerImpl implements NetworkingHandler {
	private Network type;
	private boolean connected;

	@Override
	public void initialise(GameState state, int port) {
		type = new Server(this, port, state);
	}

	@Override
	public void initialise(GameState state, String host, int port) {
		type = new Client(this, host, port, state);
	}

	@Override
	public void run() throws InterruptedException {
		type.run();
		Thread.sleep(1000);
	}

	@Override
	public void send(SaveInstance instance) throws InterruptedException {
		System.out.println("sending L2");
		type.send(instance);
		Thread.sleep(3000);
	}

	@Override
	public void stop() throws InterruptedException {
		type.stop();
	}

	@Override
	public boolean isConnected() {
		return connected;
	}

	@Override
	public void setConnected(boolean connected) {
		this.connected = connected;
	}

	/**
	 * Main method used for manual testing.
	 */
	public static void main(String[] args) throws Exception {
		NetworkingHandlerImpl handler = new NetworkingHandlerImpl();
		if (args.length == 2) {
			handler.initialise(new TestGameState(), args[0], Integer.parseInt(args[1]));
		} else if (args.length == 1) {
			handler.initialise(new TestGameState(), Integer.parseInt(args[0]));
		} else {
			throw new IllegalArgumentException();
		}
		TestSaveInstance instance = new TestSaveInstance("Testing123", 456);
		System.out.print("Type anything to continue: ");
		Scanner sc = new Scanner(System.in);
		while (true) {
			if (sc.hasNext()) break;
		}
		System.out.println(sc.next());
		handler.run();
		if (args.length == 2) {
			handler.send(instance);
		}
	}

}