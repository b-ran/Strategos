package strategos.networking.networks;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import strategos.SaveInstance;
import strategos.networking.Network;
import strategos.networking.handlers.DataHandler;

/**
 * The server used for transmitting objects
 */
public class Server implements Network {
	private int port;
    private NetworkHandler serverHandler;

	public NetworkHandler getServerHandler() {
		return serverHandler;
	}

	public Server(int port) {
		this.port = port;
		serverHandler = new NetworkHandler(this);
	}

	@Override
	public void run() throws InterruptedException {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
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
			// In this example, this does not happen, but you can do that to gracefully
			// shut down your server.
			f.channel().closeFuture().sync();
		} finally {
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
	}

	@Override
	public void send(SaveInstance instance) throws InterruptedException {
		serverHandler.send(instance);
	}

    //Is this done?
    @Override
    public void receive(SaveInstance instance) {
        System.out.println(instance);
    }
    //TODO: add java docs
}