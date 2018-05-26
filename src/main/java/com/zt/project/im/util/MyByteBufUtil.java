package com.zt.project.im.util;

import com.alibaba.fastjson.JSON;
import com.google.protobuf.ByteString;
import com.zt.project.im.annotation.CustomServiceAnnotation;
import com.zt.project.im.protobuf.Message;
import org.apache.log4j.Logger;
import org.springframework.core.annotation.AnnotationUtils;

/**
 * ZhangTao
 * 2018/5/23 22:02
 * Description:
 */
public class MyByteBufUtil {

    private static Logger logger = Logger.getLogger(MyByteBufUtil.class);

//    public static

    /**
     * 根据注解获取类型
     * @param clazz
     * @return
     */
    public static Integer getMsgTypeByAnnotation(Class clazz){
        CustomServiceAnnotation annotation = AnnotationUtils
                .findAnnotation(clazz,CustomServiceAnnotation.class);
        if(annotation != null){
           Integer type = annotation.type();
           return type;
        }else {
            logger.warn(clazz.getClass().getName()+" don't have a CustomServiceAnnotation! Please check!");
        }
        return null;
    }

    /**
     * 根据消息类型构建baseMessage
     * @param type
     * @param byteString
     * @return
     */
    public static Message.BaseMessage buildBaseMessage(Integer type, ByteString byteString){
        Message.BaseMessage baseMessage = Message.BaseMessage.newBuilder()
                .setMsgType(type)
                .setBytesData(byteString)
                .build();
        return baseMessage;
    }
}
