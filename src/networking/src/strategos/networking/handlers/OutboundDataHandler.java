package strategos.networking.handlers;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.MessageToByteEncoder;
import strategos.SaveInstance;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

public class OutboundDataHandler extends MessageToByteEncoder<SaveInstance> {
	@Override
	protected void encode(ChannelHandlerContext ctx, SaveInstance msg, ByteBuf out) throws Exception {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		oos.writeObject(msg);
		oos.close();
		out.writeBytes(bos.toByteArray());
	}
}
