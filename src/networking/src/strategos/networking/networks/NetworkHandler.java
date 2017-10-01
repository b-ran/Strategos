package strategos.networking.networks;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import strategos.SaveInstance;
import strategos.networking.Network;

import java.util.ArrayList;
import java.util.List;

//Either rename or move to handlers
public class NetworkHandler extends SimpleChannelInboundHandler<SaveInstance> {

	private List<ChannelHandlerContext> connections = new ArrayList<>();
	private Network network;

	public NetworkHandler(Network network) {
		this.network = network;
	}

	@Override
	public void channelActive(final ChannelHandlerContext ctx) throws Exception {
		super.channelActive(ctx);
		connections.add(ctx);
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		super.channelInactive(ctx);
		connections.remove(ctx);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}


	public void send(SaveInstance instance) {
		connections.forEach(conn-> conn.writeAndFlush(instance));
	}

	@Override
	protected void messageReceived(ChannelHandlerContext ctx, SaveInstance msg) throws Exception {
        //Doesn't use ctx?
        network.receive(msg);
	}
    //TODO: add java docs
}
