package com.zt.project.im.service.im.impl;

import com.google.protobuf.ByteString;
import com.zt.project.im.annotation.CustomServiceAnnotation;
import com.zt.project.im.proto.Message;
import com.zt.project.im.service.im.IBaseMessageService;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Service;

/**
 * @Author：ZhangTao
 * @Description:
 * @Date: Created in 23:08 2018/4/12
 */
@Service
public class StudentMsgServiceImpl implements IBaseMessageService {

    @Override
    public void dealMessage(Message.BaseMessage student, ChannelHandlerContext ctx) {
        System.out.printf("I am student");
    }


    @Override
    public boolean validateParam(ByteString byteString, ChannelHandlerContext ctx) {
        return false;
    }
}
