package strategos.networking.handlers;

import strategos.SaveInstance;
import strategos.networking.Network;
import strategos.networking.NetworkingHandler;
import strategos.networking.networks.Client;
import strategos.networking.networks.Server;

public class NetworkingHandlerImpl implements NetworkingHandler {
	private Network type;

	@Override
	public void initialise(int port) {
		type = new Server(port);
	}

	@Override
	public void initialise(String host, int port) {
		type = new Client(host, port);
	}

	@Override
	public void run() throws InterruptedException {
		type.run();
	}

	public void send(SaveInstance instance) throws InterruptedException {
		type.send(instance);
	}
}