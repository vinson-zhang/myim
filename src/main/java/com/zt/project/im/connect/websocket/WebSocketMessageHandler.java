package com.zt.project.im.connect.websocket;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import org.apache.log4j.Logger;

import java.util.List;

public class WebSocketMessageHandler extends MessageToMessageDecoder<WebSocketFrame> {

    private static final Logger logger = Logger.getLogger(WebSocketMessageHandler.class);

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, WebSocketFrame frame, List list) throws Exception {
        //文本帧处理(收到的消息广播到前台客户端)
        if (frame instanceof TextWebSocketFrame) {
            logger.info("文本帧消息："+((TextWebSocketFrame) frame).text());
        }
        //二进制帧处理,将帧的内容往下传
        else if (frame instanceof BinaryWebSocketFrame) {
            System.out.println("The WebSocketFrame is BinaryWebSocketFrame");
            BinaryWebSocketFrame binaryWebSocketFrame = (BinaryWebSocketFrame) frame;
            byte[] by = new byte[frame.content().readableBytes()];
            binaryWebSocketFrame.content().readBytes(by);
            ByteBuf bytebuf = Unpooled.buffer();
            bytebuf.writeBytes(by);
            list.add(bytebuf);
        } else if(frame instanceof PingWebSocketFrame){
            System.out.println("其它帧，需要其它处理，这里不做解释");
        }
    }
}
