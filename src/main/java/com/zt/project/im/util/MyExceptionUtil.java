package com.zt.project.im.util;

import org.apache.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * ZhangTao
 * 2018/5/13 18:00
 * Description: 异常相关的工具类
 */
public class MyExceptionUtil {

    private static Logger logger = Logger.getLogger(MyExceptionUtil.class);

    public static String getStackTraceStr(Exception e){
        e.printStackTrace();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        e.printStackTrace(new PrintWriter(byteArrayOutputStream,true));
        String stackTraceStr = byteArrayOutputStream.toString();
        try {
            byteArrayOutputStream.close();
        } catch (IOException e1) {
            e1.printStackTrace();
            logger.error("自定义工具类中，获取异常的堆栈信息失败...........");
        }
        return stackTraceStr;
    }

}
