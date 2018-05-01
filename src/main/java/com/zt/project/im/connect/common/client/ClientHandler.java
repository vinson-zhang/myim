package com.zt.project.im.connect.common.client;

import com.google.protobuf.ByteString;
import com.zt.project.im.protobuf.Message;
import com.zt.project.im.protobuf.StudentOuterClass;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author：ZhangTao
 * @Description:
 * @Date: Created in 13:33 2018/4/1
 */
public class ClientHandler extends SimpleChannelInboundHandler {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        System.out.println(o);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("连接了...........");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        String testStr = "hello world! index:";
//        for(int i = 0;i<1000;i++){
//            ctx.writeAndFlush(testStr+i+"\r\n");
//        }
//        System.out.println("client send finish.........");
        Message.TextMessage textMessage = Message.TextMessage.newBuilder()
                .setMsgId("123")
                .setUserId("123")
                .setReciverId("456")
                .setContent("hello world")
                .build();
        ByteString textByteString = textMessage.toByteString();
        StudentOuterClass.Student student = StudentOuterClass.Student.newBuilder()
                .setAge(0)
                .setAddress("西安")
                .setName("mike")
                .build();
        ByteString studentByteString = student.toByteString();
        Message.BaseMessage baseMessage = Message.BaseMessage.newBuilder()
                .setMsgType(3)
                .setBytesData(studentByteString)
                .setLen(textByteString.size()+1)
                .build();

        for(int i = 0;i<1;i++){
            ctx.writeAndFlush(baseMessage);
        }
        System.out.println("client send finish.........");
    }
}
