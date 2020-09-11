package com.zt.project.im.service.im;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.zt.project.im.proto.Message;
import io.netty.channel.ChannelHandlerContext;

/**
 * @Author：ZhangTao
 * @Description:
 * @Date: Created in 22:44 2018/4/12
 */
public interface IBaseMessageService {

    /**
     * 处理消息逻辑
     * @param baseMessage
     * @param ctx
     * @throws InvalidProtocolBufferException
     */
    void dealMessage(Message.BaseMessage baseMessage, ChannelHandlerContext ctx) throws InvalidProtocolBufferException;

    /**
     * 校验参数
     * @param byteString
     * @param ctx
     * @return false 校检失败 true 校验成功
     * @throws InvalidProtocolBufferException
     */
    boolean validateParam(ByteString byteString, ChannelHandlerContext ctx) throws InvalidProtocolBufferException;
}
