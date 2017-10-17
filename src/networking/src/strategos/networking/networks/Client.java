package strategos.networking.networks;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import strategos.model.GameState;
import strategos.model.SaveInstance;
import strategos.networking.NetworkingHandler;
import strategos.networking.handlers.NetworkHandler;

/**
 * Stores the IP of the server to connect to, the port te server is running on, and handles sending and receiving
 */
public class Client implements Network {
	private NetworkingHandler handler;
	private GameState state;
	private String host;
	private int port;
	private NetworkHandler clientHandler;

	private EventLoopGroup workerGroup;

	public Client(NetworkingHandler handler, String host, int port, GameState state) {
		this.handler = handler;
		this.state = state;
		this.host = host;
		this.port = port;
		clientHandler = new NetworkHandler(this);
	}

	@Override
	public void run() {
		new Thread(() -> {
			workerGroup = new NioEventLoopGroup();
			try {
				Bootstrap b = new Bootstrap();
				b.group(workerGroup);
				b.channel(NioSocketChannel.class);
				b.option(ChannelOption.SO_KEEPALIVE, true);
				b.handler(new ChannelInitializer<SocketChannel>() {
					@Override
					public void initChannel(SocketChannel ch) throws InterruptedException {
						ch.pipeline().addLast(new ObjectEncoder(), new ObjectDecoder(ClassResolvers.cacheDisabled(null)), clientHandler);
					}
				});
				// Start the client.
				ChannelFuture f = b.connect(host, port).sync();

				// Wait until the connection is closed.
				f.channel().closeFuture().sync();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				stop();
			}
		}).start();
	}

	@Override
	public void send(SaveInstance instance) {
		clientHandler.send(instance);
	}

	@Override
	public void receive(SaveInstance instance) {
		System.out.println("client: recieved");
		state.load(instance);
	}

	@Override
	public void stop() {
		workerGroup.shutdownGracefully();
	}

	@Override
	public void setConnected(boolean connected) {
		handler.setConnected(connected);
		System.out.println("client: " + connected);
	}
}