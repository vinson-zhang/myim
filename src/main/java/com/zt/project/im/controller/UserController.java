package com.zt.project.im.controller;

import com.zt.project.im.bean.User;
import com.zt.project.im.bean.vo.UserVO;
import com.zt.project.im.service.business.IUserService;
import com.zt.project.im.util.ResponseInfo;
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
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/getUserInfo")
    public List<User> getUserInfo() {
        List<User> user = userService.getUserInfo();
        System.out.println(user.toString());
        return user;
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

    @RequestMapping("/addUserInfo")
    public ResponseInfo<User> addUserInfo(@RequestBody User user) {
        ResponseInfo<User> responseInfo = new ResponseInfo<User>();
        user.setUsername(user.getUsername());
        user.setPassword(user.getPassword());
        userService.addUser(user);
        return responseInfo;
    }

    @RequestMapping("/getUserInfoByUserName")
    public ResponseInfo<UserVO> getUserInfoByUserName(String username){
        ResponseInfo<UserVO> responseInfo = new ResponseInfo<UserVO>();
        responseInfo.setResult(userService.getUserVO(username));
        return responseInfo;
    }




}
