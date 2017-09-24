package strategos.networking.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ServerHandler extends ChannelInboundHandlerAdapter {
	@Override
	public void channelActive(final ChannelHandlerContext ctx) {
		final ByteBuf time = ctx.alloc().buffer(4);

		final ChannelFuture f = ctx.writeAndFlush(time);
		f.addListener((ChannelFutureListener) future -> {
			assert f == future;
			ctx.close();
		});
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}
}
