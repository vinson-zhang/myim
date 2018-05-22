package com.zt.project.im.connect.common;

import com.zt.project.im.listener.CustomAnnotationListener;
import com.zt.project.im.protobuf.Message;
import com.zt.project.im.service.im.IBaseMessageService;
import com.zt.project.im.util.SpringBeanUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.apache.log4j.Logger;

/**
 * @Author：ZhangTao
 * @Description:
 * @Date: Created in 11:21 2018/4/1
 */
public class ServerHandler extends SimpleChannelInboundHandler<Message.BaseMessage> {

    //存放所有channel对象
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    private static final Logger logger = Logger.getLogger(ServerHandler.class);

    private static Integer count = 0;


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Message.BaseMessage baseMessage) throws Exception {
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
           baseMessageService.dealMessage(baseMessage,channelHandlerContext);
       }else {
           logger.error("baseMessage is null!");
       }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().id()+"连接建立了............");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().id()+"连接中断............");
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.warn("异常异常...",cause);
    }
}
