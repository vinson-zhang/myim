package com.zt.project.im.connect.websocket;

import com.zt.project.im.connect.common.ServerHandler;
import com.zt.project.im.listener.CustomAnnotationListener;
import com.zt.project.im.protobuf.Message;
import com.zt.project.im.service.im.IBaseMessageService;
import com.zt.project.im.util.SpringBeanUtils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class WebSocketServerHandler extends SimpleChannelInboundHandler<Message.BaseMessage>{

	private static final Logger logger = Logger.getLogger(ServerHandler.class);

	//存放所有channel对象
	private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

	private static Map<String,ChannelHandlerContext> channelHashMap = new HashMap<String,ChannelHandlerContext>();

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Message.BaseMessage baseMessage) throws Exception {
		System.out.println("===================================");
		System.out.println("收到的消息:"+baseMessage);
//		Message.TextMessage textMessage = msg.toBuilder().setContent("服务器收到了....").build();

		if(baseMessage != null){
			int type = baseMessage.getMsgType();
			String beanName = CustomAnnotationListener.messageDealServiceMap.get(type);
			if(beanName == null){
				logger.error("the type["+type+"] was not defined!");
				return;
			}
			IBaseMessageService baseMessageService = (IBaseMessageService) SpringBeanUtils.getBean(beanName);
			if(baseMessageService == null){
				logger.error("the bean["+beanName+"] was not found! please confirm!");
			}
			baseMessageService.dealMessage(baseMessage, ctx);
		}else {
			logger.error("baseMessage is null!");
		}

		for (String key : channelHashMap.keySet()){
			Channel channel = channelHashMap.get(key).channel();
			if(channel.isActive() && !ctx.toString().equals(key)){
//			if(channel.isActive()){
				System.out.println("writeTo:"+channel.id());
				channel.writeAndFlush(baseMessage);
			}
		}

//		channelGroup.find()

//		ctx.channel().writeAndFlush(baseMessage);
//		byte[] msgBytes = msg.toByteArray();
//		ByteBuf byteBuf = Unpooled.buffer().readBytes(msgBytes);
//		ctx.channel().writeAndFlush(new BinaryWebSocketFrame(byteBuf));
	}
	
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		System.out.println("handlerAdded:"+ctx.channel().id().asLongText());
	}
	
	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		System.out.println("handlerRemoved:"+ctx.channel().id().asLongText());
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		super.channelActive(ctx);
		channelHashMap.put(ctx.toString(),ctx);
		System.out.println("wolianjiele*****************************");
	}


	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.out.println("异常发生");
		channelHashMap.remove(ctx.toString());
		ctx.close();
		System.out.println(channelHashMap.size());
	}
	
}
