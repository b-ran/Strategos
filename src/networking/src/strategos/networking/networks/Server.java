package strategos.networking.networks;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import strategos.GameState;
import strategos.SaveInstance;
import strategos.networking.NetworkingHandler;
import strategos.networking.handlers.DataHandler;
import strategos.networking.handlers.NetworkHandler;
import strategos.networking.handlers.NetworkingHandlerImpl;

/**
 * Used to host the server so clients can send objects to here, and here send messages to clients
 */
public class Server implements Network {
	private NetworkingHandler handler;
	private int port;
	private NetworkHandler serverHandler;
	private GameState state;

	private EventLoopGroup workerGroup;
	private EventLoopGroup bossGroup;

	public Server(NetworkingHandlerImpl handler, int port, GameState state) {
		this.handler = handler;
		this.port = port;
		this.state = state;
		serverHandler = new NetworkHandler(this);
	}

	@Override
	public void run() {
		new Thread(() -> {
			bossGroup = new NioEventLoopGroup();
			workerGroup = new NioEventLoopGroup();
			try {
				ServerBootstrap b = new ServerBootstrap();
				b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
						.childHandler(new ChannelInitializer<SocketChannel>() {
							@Override
							public void initChannel(SocketChannel ch) throws Exception {
								ch.pipeline().addLast(new DataHandler(), serverHandler);
							}
						})
						.option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, true);
				// Bind and start to accept incoming connections.
				ChannelFuture f = b.bind(port).sync();
				// Wait until the server socket is closed.
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
		serverHandler.send(instance);
	}

	@Override
	public void receive(SaveInstance instance) {
		state.load(instance);
	}

	@Override
	public void stop() {
		workerGroup.shutdownGracefully();
		bossGroup.shutdownGracefully();
	}

	@Override
	public void setConnected(boolean connected) {
		handler.setConnected(connected);
		System.out.println("Server: " + connected);
	}
}