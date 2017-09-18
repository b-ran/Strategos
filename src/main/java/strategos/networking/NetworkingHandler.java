package strategos.networking;

public class NetworkingHandler {
	public NetworkingHandler(int port) {
		try {
			new StrategosServer(port).run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new NetworkingHandler(args.length > 0 ? Integer.parseInt(args[0]) : 8080);
	}
}
