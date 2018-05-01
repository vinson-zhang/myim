package com.zt.project.im.enumpack;

import org.junit.Test;

/**
 * @Author：ZhangTao
 * @Description:
 * @Date: Created in 13:06 2018/4/14
 */
public class TestEnum {

    @Test
    public void test(){
        System.out.println(MessageTypeEnum.TEXT_MESSAGE);
        System.out.println(MessageTypeEnum.TEXT_MESSAGE.equals(MessageTypeEnum.IMAGE_MESSAGE));
    }

    @Test
    public void testSwitch(){
        switch (MessageTypeEnum.TEXT_MESSAGE){
            case TEXT_MESSAGE:
                System.out.println("text");
                break;
            case IMAGE_MESSAGE:
                System.out.println("image");
                break;
            case VOICE_MESSAGE:
                System.out.println("voice");
                break;
        }
    }

    @Test
    public void customFunction(){
        System.out.println(MessageTypeEnum.TEXT_MESSAGE.getIndex());
        System.out.println(MessageTypeEnum.TEXT_MESSAGE.getType());
    }
}
