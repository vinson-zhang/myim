package com.zt.project.im.controller;

import com.zt.project.im.bean.User;
import com.zt.project.im.bean.vo.UserVO;
import com.zt.project.im.enumpack.ErrorCodeEnum;
import com.zt.project.im.service.business.IUserService;
import com.zt.project.im.util.ResponseInfo;
import io.swagger.annotations.Api;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author：ZhangTao
 * @Description:
 * @Date: Created in 13:14 2018/3/26
 */
@Api
@RestController
@RequestMapping("/user")
public class UserController {

    private static Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    public ResponseInfo<UserVO> getUserInfo() {
        ResponseInfo<UserVO> responseInfo = new ResponseInfo<UserVO>();
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        if(username == null){
            responseInfo.setCode(ErrorCodeEnum.OTHER_ERROR.getCode());
            responseInfo.setDesc(ErrorCodeEnum.OTHER_ERROR.getDesc());
            return responseInfo;
        }
        User user = new User();
        user.setUsername(username);
        UserVO userVO = userService.getUserVO(user);
        responseInfo.setResult(userVO);
        return responseInfo;
    }

    @RequestMapping(value = "/getUserInfoByUser",method = RequestMethod.POST)
    public ResponseInfo<UserVO> getUserInfoById(@RequestBody User user) {
//    public ResponseInfo<UserVO> getUserInfoById(Integer userId,String username) {
        ResponseInfo<UserVO> responseInfo = new ResponseInfo<UserVO>();
//        User user = new User();
//        user.setId(userId);
//        user.setUsername(username);
        UserVO userVO = userService.getUserVO(user);
        responseInfo.setResult(userVO);
        return responseInfo;
    }

    @RequestMapping(value = "/addUserInfo", method = RequestMethod.POST)
    public ResponseInfo<User> addUserInfo(@RequestBody User user) {
        ResponseInfo<User> responseInfo = new ResponseInfo<User>();
        user.setUsername(user.getUsername());
        user.setPassword(user.getPassword());
        userService.addUser(user);
        return responseInfo;
    }

    @RequestMapping(value = "/getUserInfoByUserName", method = RequestMethod.GET)
    public ResponseInfo<UserVO> getUserInfoByUserName(String username){
        ResponseInfo<UserVO> responseInfo = new ResponseInfo<UserVO>();
        responseInfo.setResult(userService.getUserVO(username));
        return responseInfo;
    }




}
