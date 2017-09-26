package strategos.networking.handlers;

import strategos.SaveInstance;
import strategos.networking.Network;
import strategos.networking.NetworkHandler;
import strategos.networking.client.Client;
import strategos.networking.server.Server;
import strategos.networking.server.ServerHandler;

public class NetworkHandlerImpl implements NetworkHandler {
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

	@Override
	public Network getType() {
		return type;
	}

	public void send(SaveInstance instance) throws InterruptedException {
		type.send(instance);
	}
}