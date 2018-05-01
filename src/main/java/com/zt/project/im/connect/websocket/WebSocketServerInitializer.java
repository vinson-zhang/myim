package com.zt.project.im.connect.websocket;

import com.zt.project.im.protobuf.Message;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class WebSocketServerInitializer extends ChannelInitializer<SocketChannel>{
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
//		pipeline.addFirst(new LoggingHandler(LogLevel.INFO));
//		pipeline.addLast(new HttpServerCodec());
//		pipeline.addLast(new ChunkedWriteHandler());
//		pipeline.addLast(new HttpObjectAggregator(8192));
//		pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
//		pipeline.addLast(new WebSocketServerHandler());
//		pipeline.addFirst(new LoggingHandler(LogLevel.INFO));           //打印日志,可以看到websocket帧数据
		pipeline.addLast(new HttpServerCodec());                        //将请求和应答消息编码或解码为HTTP消息
		pipeline.addLast(new HttpObjectAggregator(65536));              //将HTTP消息的多个部分组合成一条完整的HTTP消息
		pipeline.addLast(new WebSocketServerCompressionHandler());
		pipeline.addLast(new WebSocketServerProtocolHandler("/ws",null,true));  //Netty支持websocket
		pipeline.addLast(new WebSocketMessageHandler());                 //websocket消息帧处理看下面代码(这里需要把前台的消息分类，判断传过来的是websocket哪个帧，如果为二进制帧往下传值，让protobuf解码)
		pipeline.addLast(new ProtobufDecoder(Message.BaseMessage.getDefaultInstance()));    //protbuf解码
		pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());             //半包处理
		pipeline.addLast(new ProtobufEncoder());
		pipeline.addLast(new WebSocketServerHandler());                          //消息处理和发送protobuf信息
	}
}
