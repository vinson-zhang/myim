package com.zt.project.im.controller;

import com.zt.project.im.bean.User;
import com.zt.project.im.util.ErrorCodeEnum;
import com.zt.project.im.util.ResponseInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * ZhangTao
 * 2018/5/13 20:08
 * Description: loginController
 */
@Controller
@RestController
@RequestMapping(value = "/login")
public class LoginController {

    @RequestMapping(method = RequestMethod.GET)
    public ResponseInfo notLogin(){
        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setCode(ErrorCodeEnum.NOT_LOGIN.getCode());
        responseInfo.setDesc(ErrorCodeEnum.NOT_LOGIN.getDesc());
        return responseInfo;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseInfo loginReq(@RequestBody User user){
        ResponseInfo responseInfo = new ResponseInfo();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUsername(),user.getPassword());
        subject.login(usernamePasswordToken);
        return responseInfo;
    }

}
