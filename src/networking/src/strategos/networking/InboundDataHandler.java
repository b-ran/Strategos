package strategos.networking;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import strategos.GameCollections;

import java.io.ObjectInputStream;

public class InboundDataHandler extends ChannelInboundHandlerAdapter {
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ObjectInputStream in = new ObjectInputStream(new ByteBufInputStream((ByteBuf) msg));
		GameCollections out = (GameCollections) in.readObject();
		System.out.println(out);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}