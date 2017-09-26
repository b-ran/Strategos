package strategos.networking.handlers;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import strategos.SaveInstance;

public class InboundDataHandler extends SimpleChannelInboundHandler<SaveInstance> {

	@Override
	protected void messageReceived(ChannelHandlerContext ctx, SaveInstance msg) throws Exception {

	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}