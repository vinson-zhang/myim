package com.zt.project.im.connect.common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author：ZhangTao
 * @Description:
 * @Date: Created in 11:30 2018/4/1
 */
@Component
@ConfigurationProperties(prefix = "netty")
public class NettyConfig {

    private int port;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
