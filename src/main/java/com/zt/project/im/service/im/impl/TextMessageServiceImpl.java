package com.zt.project.im.service.im.impl;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.zt.project.im.annotation.CustomServiceAnnotation;
import com.zt.project.im.protobuf.Message;
import com.zt.project.im.service.im.IBaseMessageService;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Service;

/**
 * @Author：ZhangTao
 * @Description:
 * @Date: Created in 22:45 2018/4/12
 */
@CustomServiceAnnotation(type = 1)
@Service
public class TextMessageServiceImpl implements IBaseMessageService {

    @Override
    public void dealMessage(Message.BaseMessage textMessage, ChannelHandlerContext ctx) throws InvalidProtocolBufferException {
        System.out.println(textMessage);
        ByteString byteString = textMessage.getBytesData();
        Message.TextMessage textMessage1 = Message.TextMessage.parseFrom(byteString);
        System.out.println(textMessage1);
    }
}
