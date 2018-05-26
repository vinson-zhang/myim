package com.zt.project.im.connect;

import io.netty.channel.ChannelHandlerContext;

import java.util.HashMap;
import java.util.Map;

/**
 * ZhangTao
 * 2018/5/23 21:42
 * Description:
 */
public class UserConnectInfo {

    /**
     * 用户ID--连接
     */
    public static Map<Integer,ChannelHandlerContext> userToChannel = new HashMap<Integer, ChannelHandlerContext>();

    /**
     * 连接---用户ID
     */
    public static Map<String,Integer> channelToUser = new HashMap<String, Integer>();


    /**
     * 根据用户信息获取有效连接
     * @param userId
     * @return
     */
    public static ChannelHandlerContext getChannelHandlerContextByUserId(Integer userId){
        ChannelHandlerContext channelHandlerContext = userToChannel.get(userId);
        if(channelHandlerContext != null && !channelHandlerContext.isRemoved()){
            return channelHandlerContext;
        }
        return null;
    }

//    public void addUserToChannel(String userId, ChannelHandlerContext ctx){
//        userToChannel.put(userId,ctx);
//    }
//
//    public void removeUserToChannel(String userId){
//        userToChannel.remove(userId);
//    }
//
//    public

}
