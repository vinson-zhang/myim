package com.zt.project.im.service.im.impl;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.zt.project.im.annotation.CustomServiceAnnotation;
import com.zt.project.im.bean.User;
import com.zt.project.im.connect.UserConnectInfo;
import com.zt.project.im.enumpack.ErrorCodeEnum;
import com.zt.project.im.protobuf.Message;
import com.zt.project.im.service.im.IBaseMessageService;
import com.zt.project.im.util.MyByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * ZhangTao
 * 2018/5/14 23:10
 * Description: 登录IM
 */
@CustomServiceAnnotation(type = 1)
@Service
public class LoginReqServiceImpl implements IBaseMessageService {


    @Override
    public void dealMessage(Message.BaseMessage baseMessage, ChannelHandlerContext ctx) throws InvalidProtocolBufferException {
        Message.LoginRes.Builder resp = Message.LoginRes.newBuilder();

        ByteString byteString = baseMessage.getBytesData();
        Message.LoginReq loginReq = Message.LoginReq.parseFrom(byteString);
        if(validateParam(byteString, ctx)){
            int userId = loginReq.getUserId();
            UserConnectInfo.userToChannel.put(userId,ctx);
            UserConnectInfo.channelToUser.put(ctx.channel().id().toString(),userId);
            resp.setCode(ErrorCodeEnum.SUCCESS.getCode());
            resp.setDesc(ErrorCodeEnum.SUCCESS.getDesc());
            ctx.writeAndFlush(MyByteBufUtil.buildBaseMessage(MyByteBufUtil.getMsgTypeByAnnotation(LoginReqServiceImpl.class),resp.build().toByteString()));
        }
    }

    @Override
    public boolean validateParam(ByteString byteString, ChannelHandlerContext ctx) throws InvalidProtocolBufferException {
        Message.LoginReq loginReq = Message.LoginReq.parseFrom(byteString);
        int userId = loginReq.getUserId();
        if(userId <= 0){
            Message.LoginRes.Builder resp = Message.LoginRes.newBuilder();
            resp.setCode(ErrorCodeEnum.PARAM_INVALID.getCode());
            resp.setDesc(ErrorCodeEnum.PARAM_INVALID.getDesc());
            ctx.writeAndFlush(MyByteBufUtil.buildBaseMessage(MyByteBufUtil.getMsgTypeByAnnotation(LoginReqServiceImpl.class),resp.build().toByteString()));
            return false;
        }
        return true;
    }
}
