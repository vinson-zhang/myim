package com.zt.project.im.connect.websocket;

import com.zt.project.im.connect.common.ServerHandler;
import com.zt.project.im.enumpack.ErrorCodeEnum;
import com.zt.project.im.listener.CustomAnnotationListener;
import com.zt.project.im.proto.Message;
import com.zt.project.im.service.im.IBaseMessageService;
import com.zt.project.im.service.im.ImProtoDealStrategyFactory;
import com.zt.project.im.util.MyByteBufUtil;
import com.zt.project.im.util.SpringBeanUtils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class WebSocketServerHandler extends SimpleChannelInboundHandler<Message.BaseMessage>{

	private static final Logger logger = Logger.getLogger(ServerHandler.class);

	//存放所有channel对象
	private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

	private static Map<String,ChannelHandlerContext> channelHashMap = new HashMap<String,ChannelHandlerContext>();

	@Autowired
	private ImProtoDealStrategyFactory imProtoDealStrategyFactory;

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Message.BaseMessage baseMessage) throws Exception {
		System.out.println("===================================");
		System.out.println("收到的消息:"+baseMessage);
		try {
			Message.BaseMessage.MsgType msgType = baseMessage.getMsgType();
			IBaseMessageService strategyByType = imProtoDealStrategyFactory.getStrategyByType(msgType);
			if(strategyByType == null){
				ctx.writeAndFlush(MyByteBufUtil.buildErrorResp(ErrorCodeEnum.PARAM_INVALID));
				return;
			}
			strategyByType.dealMessage(baseMessage,ctx);
		}catch (Exception e){
			ctx.writeAndFlush(MyByteBufUtil.buildErrorResp(ErrorCodeEnum.OTHER_ERROR));
		}
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
		System.out.println("*****************************connId:"+ctx.channel().id());
	}


	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.out.println("异常发生");
		channelHashMap.remove(ctx.toString());
		ctx.close();
		System.out.println(channelHashMap.size());
	}
	
}
