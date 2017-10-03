package strategos.networking;

import org.junit.jupiter.api.Test;
import strategos.SaveInstance;
import strategos.networking.handlers.NetworkingHandlerImpl;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

class Tests {
	@Test
	void testPacketSerialization() throws IOException, ClassNotFoundException {
		SaveInstance msg = new TestSaveInstance("This is for testing purposes", 15);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		oos.writeObject(msg);
		oos.close();
		SaveInstance out;
		ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
		out = (SaveInstance) ois.readObject();
		ois.close();
		assertTrue(out.equals(msg));
	}

	/**
	 * This test requires the user to check the output manually
	 */
	@Test
	void testSendFromServer() throws InterruptedException {
		NetworkingHandler server = new NetworkingHandlerImpl();
		NetworkingHandler client = new NetworkingHandlerImpl();
		server.initialise(new TestGameState(), 8080);
		client.initialise(new TestGameState(), "127.0.0.1", 8080);
		server.run();
		client.run();
		TestSaveInstance instance = new TestSaveInstance("Testing123", 456);
		System.out.println("Should be " + instance.i + " and " + instance.s);
		server.send(instance);
	}
}
