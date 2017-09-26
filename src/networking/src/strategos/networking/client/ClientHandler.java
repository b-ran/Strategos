package strategos.networking.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import strategos.GameState;
import strategos.SaveInstance;

public class ClientHandler extends SimpleChannelInboundHandler<SaveInstance> {

	@Override
	protected void messageReceived(ChannelHandlerContext ctx, SaveInstance msg) throws Exception {
		System.out.println(msg.getPlayers());
		System.out.println(msg.getTurn());
		System.out.println(msg.getWorld());
		ctx.close();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
