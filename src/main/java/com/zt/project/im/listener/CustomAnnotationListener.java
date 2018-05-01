package com.zt.project.im.listener;

import com.alibaba.fastjson.JSON;
import com.zt.project.im.annotation.CustomServiceAnnotation;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author：ZhangTao
 * @Description: 扫描自定义注解@CustomAnnotation
 * @Date: Created in 13:23 2018/4/14
 */
@Component
public class CustomAnnotationListener implements BeanPostProcessor {

    public static Map<Integer,String> messageDealServiceMap;

    static {
        if(messageDealServiceMap == null){
            messageDealServiceMap = new HashMap<Integer, String>();
        }
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        CustomServiceAnnotation annotation = AnnotationUtils
                .findAnnotation(bean.getClass(),CustomServiceAnnotation.class);
        if(annotation != null){
            synchronized (messageDealServiceMap){
                int type = annotation.type();
                //是否已经存在这个类型
                if(messageDealServiceMap.containsKey(type)){
                    throw new RuntimeException("the type[type="+type+";beanName="+beanName+"] is exist,please change the type! all type:"+JSON.toJSONString(messageDealServiceMap));
                }else {
                    messageDealServiceMap.put(type,beanName);
                }
//                System.out.println(JSON.toJSON(messageDealServiceMap));
            }
        }
        return bean;
    }
}
