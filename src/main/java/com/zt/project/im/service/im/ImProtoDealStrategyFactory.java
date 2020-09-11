/**
 * @Author: Vinson
 * @Date: 2020/9/11
 * @Description:
 */

package com.zt.project.im.service.im;

import com.zt.project.im.bean.constant.GlobalConstant;
import com.zt.project.im.proto.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class ImProtoDealStrategyFactory {

    private static Map<String, IBaseMessageService> serviceMap;

    @Autowired
    public void setServiceMap(Map<String, IBaseMessageService> serviceMap){
        ImProtoDealStrategyFactory.serviceMap = serviceMap;
    }


    public static IBaseMessageService getStrategyByType(Message.BaseMessage.MsgType msgType){
        String key = GlobalConstant.STRATEGY_PREFIX_PROTO_DEAL + msgType.getNumber();
        IBaseMessageService messageService = serviceMap.get(key);
        return messageService;
    }


}
