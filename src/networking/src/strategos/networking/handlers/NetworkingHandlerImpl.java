package strategos.networking.handlers;

import strategos.SaveInstance;
import strategos.networking.Network;
import strategos.networking.NetworkingHandler;
import strategos.networking.SaveInstanceImpl;
import strategos.networking.networks.Client;
import strategos.networking.networks.Server;

import java.util.Scanner;

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
		new Thread(() -> {
			try {
				type.run();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
		Thread.sleep(1000);
	}

	@Override
	public void send(SaveInstance instance) throws InterruptedException {
		type.send(instance);
		Thread.sleep(3000);
	}

	public static void main(String[] args) throws Exception {
		NetworkingHandlerImpl handler = new NetworkingHandlerImpl();
		if(args.length == 2) {
			handler.initialise(args[0], Integer.parseInt(args[1]));
		} else if (args.length == 1) {
			handler.initialise(Integer.parseInt(args[0]));
		} else {
			throw new IllegalArgumentException();
		}
		SaveInstanceImpl instance = new SaveInstanceImpl("Testing123", 456);
		System.out.print("Type anything to continue: ");
		Scanner sc = new Scanner(System.in);
		while(!sc.hasNext()) {}
		System.out.println(sc.next());
		handler.run();
		if(args.length == 2) {
			handler.send(instance);
		}
	}
}