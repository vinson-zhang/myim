package com.zt.project.im.connect.common;

/**
 * @Author：ZhangTao
 * @Description:
 * @Date: Created in 11:28 2018/4/1
 */
public class NettyConstant {
    /**
     * 最大线程量
     */
    private static final int MAX_THREADS = 1024;
    /**
     * 数据包最大长度
     */
    private static final int MAX_FRAME_LENGTH = 65535;


    public static int getMaxFrameLength() {
        return MAX_FRAME_LENGTH;
    }

    public static int getMaxThreads() {
        return MAX_THREADS;
    }

}
