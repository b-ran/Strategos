package strategos.networking;

import org.junit.Test;
import strategos.SaveInstance;
import strategos.networking.handlers.NetworkingHandlerImpl;

import java.io.*;

import static org.junit.Assert.assertTrue;

public class Tests {
	@Test
	public void testPacketSerialization() throws IOException, ClassNotFoundException {
		SaveInstance msg = new SaveInstanceImpl("This is for testing purposes", 15);
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

	@Test
	public void testSendFromServer() throws InterruptedException {
		NetworkingHandler server = new NetworkingHandlerImpl();
		NetworkingHandler client = new NetworkingHandlerImpl();
		server.initialise(8080);
		client.initialise("127.0.0.1", 8080);
		server.run();
		client.run();
		SaveInstanceImpl instance = new SaveInstanceImpl("Testing123", 456);
		System.out.println("Should be " + instance.s + " and " + instance.i);
		server.send(instance);
	}
}
