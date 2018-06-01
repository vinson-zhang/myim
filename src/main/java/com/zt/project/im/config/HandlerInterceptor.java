package com.zt.project.im.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Descriprion: TODO
 * @author: zhangxiaohua
 * @create 2018/5/31 22:34
 **/
@Component
public class HandlerInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println(request.getMethod());
        if("OPTIONS".equals(request.getMethod())){
            System.out.println("=================");
            return false;
        }
        return super.preHandle(request, response, handler);
    }
}
