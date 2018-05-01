package com.zt.project.im.service.impl;

import com.zt.project.im.annotation.CustomServiceAnnotation;
import com.zt.project.im.protobuf.Message;
import com.zt.project.im.protobuf.StudentOuterClass;
import com.zt.project.im.service.IBaseMessageService;
import org.springframework.stereotype.Service;

/**
 * @Author：ZhangTao
 * @Description:
 * @Date: Created in 23:08 2018/4/12
 */
@CustomServiceAnnotation(type = 2)
@Service
public class StudentMsgServiceImpl implements IBaseMessageService {

    @Override
    public void dealMessage(Message.BaseMessage student) {
        System.out.printf("I am student");
    }
}
