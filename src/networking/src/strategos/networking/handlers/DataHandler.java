package strategos.networking.handlers;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.ByteToMessageCodec;
import strategos.SaveInstance;

import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * This class handles decoding all the incoming data, and encoding all the outgoing data.
 */
public class DataHandler extends ByteToMessageCodec<SaveInstance> {
	@Override
	protected void encode(ChannelHandlerContext ctx, SaveInstance msg, ByteBuf out) throws Exception {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		oos.writeObject(msg);
		oos.close();
		out.writeBytes(bos.toByteArray());
	}

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		ObjectInputStream ois = new ObjectInputStream(new ByteBufInputStream(in));
		out.add(ois.readObject());
		ois.close();
	}
}
