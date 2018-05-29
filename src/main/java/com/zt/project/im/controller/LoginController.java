package com.zt.project.im.controller;

import com.zt.project.im.bean.User;
import com.zt.project.im.enumpack.ErrorCodeEnum;
import com.zt.project.im.service.business.IUserService;
import com.zt.project.im.util.ResponseInfo;
import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * ZhangTao
 * 2018/5/13 20:08
 * Description: loginController
 */
@Api
@RestController
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    private IUserService userService;

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
        user = userService.getUser(user.getUsername());
        Map<String,Integer> userMap = new HashMap<String, Integer>();
        userMap.put("userId",user.getId());
        responseInfo.setResult(userMap);
        return responseInfo;
    }

}
