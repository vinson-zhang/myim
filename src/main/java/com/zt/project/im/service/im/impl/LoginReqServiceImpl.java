package com.zt.project.im.service.im.impl;

import com.google.protobuf.InvalidProtocolBufferException;
import com.zt.project.im.annotation.CustomServiceAnnotation;
import com.zt.project.im.protobuf.Message;
import com.zt.project.im.service.im.IBaseMessageService;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * ZhangTao
 * 2018/5/14 23:10
 * Description: 登录IM
 */
@CustomServiceAnnotation(type = 3)
@Service
public class LoginReqServiceImpl implements IBaseMessageService {

//    public static Map<String,ChannelHandlerContext>

    @Override
    public void dealMessage(Message.BaseMessage baseMessage, ChannelHandlerContext ctx) throws InvalidProtocolBufferException {

    }
}
