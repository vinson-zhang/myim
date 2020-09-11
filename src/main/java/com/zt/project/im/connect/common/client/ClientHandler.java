package com.zt.project.im.connect.common.client;

import com.google.protobuf.ByteString;
import com.zt.project.im.proto.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author：ZhangTao
 * @Description:
 * @Date: Created in 13:33 2018/4/1
 */
public class ClientHandler extends SimpleChannelInboundHandler<Message.BaseMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Message.BaseMessage baseMessage) throws Exception {
        ByteString bytesData = baseMessage.getBytesData();
        Message.MessageRes messageResp = Message.MessageRes.parseFrom(bytesData);
        System.out.println(messageResp);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("连接了...........");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client send finish.........");
        Message.TextMessageReq textMessage = Message.TextMessageReq.newBuilder()
                .setMsgId("123")
                .setUserId(123456)
                .setReciverId(456123)
                .setContent("hello world")
                .build();
        ByteString textByteString = textMessage.toByteString();
        Message.BaseMessage baseMessage = Message.BaseMessage.newBuilder()
                .setMsgType(Message.BaseMessage.MsgType.TEXT_MESSAGE_REQ)
                .setBytesData(textByteString)
                .build();

        for(int i = 0;i<1;i++){
            ctx.writeAndFlush(baseMessage);
        }
        System.out.println("client send finish.........");
    }
}
