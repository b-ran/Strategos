package strategos.networking;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import org.junit.Test;
import strategos.SaveInstance;
import strategos.networking.NetworkingHandler;
import strategos.networking.handlers.NetworkingHandlerImpl;

import java.io.*;

import static org.junit.Assert.assertTrue;

public class Tests {
	@Test
	public void testSendFromServer() throws InterruptedException {
		//Test does not work yet
		NetworkingHandler server = new NetworkingHandlerImpl();
		NetworkingHandler client = new NetworkingHandlerImpl();
		server.initialise(8080);
		client.initialise("localhost", 8080);
		new Thread(() -> {
			try {
				server.run();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).run();
		new Thread(() -> {
			try {
				client.run();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		try {
			SaveInstance instance = SaveInstance.class.newInstance();
			server.send(instance);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testPacketSerialization() throws IOException, ClassNotFoundException {
		SaveInstance msg = new SaveInstanceImpl();
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
}
