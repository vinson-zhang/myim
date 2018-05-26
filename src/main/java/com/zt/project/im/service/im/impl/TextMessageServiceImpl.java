package com.zt.project.im.service.im.impl;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.zt.project.im.annotation.CustomServiceAnnotation;
import com.zt.project.im.connect.UserConnectInfo;
import com.zt.project.im.enumpack.ErrorCodeEnum;
import com.zt.project.im.protobuf.Message;
import com.zt.project.im.service.im.IBaseMessageService;
import com.zt.project.im.util.MyByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @Author：ZhangTao
 * @Description:
 * @Date: Created in 22:45 2018/4/12
 */
@CustomServiceAnnotation(type = 5)
@Service
public class TextMessageServiceImpl implements IBaseMessageService {

    private static Logger logger = Logger.getLogger(TextMessageServiceImpl.class);

    @Override
    public void dealMessage(Message.BaseMessage baseMessage, ChannelHandlerContext ctx) throws InvalidProtocolBufferException {
        ByteString byteString = baseMessage.getBytesData();
        if(validateParam(byteString,ctx)){
            Message.TextMessageReq textMessageReq = Message.TextMessageReq.parseFrom(byteString);
            Integer reciverId = textMessageReq.getReciverId();
            ChannelHandlerContext reciverCtx = UserConnectInfo.getChannelHandlerContextByUserId(reciverId);
            if(ctx != null){
                reciverCtx.writeAndFlush(baseMessage);
                //TODO：存储消息
            }else {
                logger.info("user:"+reciverId+" is not online!");
                //todo:离线消息逻辑 和 存储消息
            }
            Message.MessageRes messageRes = Message.MessageRes.newBuilder()
                    .setMessageId(textMessageReq.getMsgId())
                    .setCode(ErrorCodeEnum.SUCCESS.getCode())
                    .setDesc(ErrorCodeEnum.SUCCESS.getDesc())
                    .build();
            ctx.writeAndFlush(messageRes);
        }
    }

    @Override
    public boolean validateParam(ByteString byteString, ChannelHandlerContext ctx) throws InvalidProtocolBufferException {
        Message.TextMessageReq textMessageReq = Message.TextMessageReq.parseFrom(byteString);
        if(textMessageReq.getUserId() <= 0 || textMessageReq.getReciverId() <= 0
                || StringUtils.isEmpty(textMessageReq.getMsgId())){
            Message.MessageRes messageRes = Message.MessageRes.newBuilder()
                    .setMessageId(textMessageReq.getMsgId())
                    .setCode(ErrorCodeEnum.PARAM_INVALID.getCode())
                    .setDesc(ErrorCodeEnum.PARAM_INVALID.getDesc())
                    .build();
            ctx.writeAndFlush(MyByteBufUtil.buildBaseMessage(MyByteBufUtil.getMsgTypeByAnnotation(TextMessageServiceImpl.class),messageRes.toByteString()));
            return false;
        }
        return true;
    }
}
