package com.zt.project.im.service.business;

import com.zt.project.im.bean.Friend;
import com.zt.project.im.bean.vo.UserFriendVO;

import java.util.List;

/**
 * ZhangTao
 * 2018/5/22 20:08
 * Description:
 */
public interface IFriendService {

    /**
     * 添加好友
     * @param friend
     */
    void addFriend(Friend friend);

    /**
     * 同意添加好友
     * @param friend
     */
    void agreeFriend(Friend friend);

    /**
     * 获取好友列表
     * @return
     */
    List<UserFriendVO> getUserFriend(Integer userId);
}
