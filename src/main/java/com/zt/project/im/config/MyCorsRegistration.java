package com.zt.project.im.config;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistration;

/**
 * @Descriprion: TODO
 * @author: zhangxiaohua
 * @create 2018/5/31 22:23
 **/
public class MyCorsRegistration extends CorsRegistration {
    public MyCorsRegistration(String pathPattern) {
        super(pathPattern);
    }

    @Override
    public CorsConfiguration getCorsConfiguration() {
        return super.getCorsConfiguration();
    }
}
