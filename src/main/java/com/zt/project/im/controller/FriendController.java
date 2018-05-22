package com.zt.project.im.controller;

import com.zt.project.im.bean.Friend;
import com.zt.project.im.service.business.IFriendService;
import com.zt.project.im.util.ResponseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * ZhangTao
 * 2018/5/22 21:12
 * Description:好友关系
 */
@RestController
@RequestMapping(value = "/friend")
public class FriendController {

    @Autowired
    private IFriendService friendService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ResponseInfo addFriend(@RequestBody Friend friend){
        friendService.addFriend(friend);
        ResponseInfo responseInfo = new ResponseInfo();
        return responseInfo;
    }

    @RequestMapping(value = "/agree",method = RequestMethod.POST)
    public ResponseInfo agreeFriend(@RequestBody Friend friend){
        ResponseInfo responseInfo = new ResponseInfo();
        friendService.agreeFriend(friend);
        return responseInfo;
    }

}
