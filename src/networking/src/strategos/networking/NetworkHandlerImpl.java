package strategos.networking;

import strategos.networking.client.Client;
import strategos.networking.server.Server;

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
}