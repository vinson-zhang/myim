package com.zt.project.im.dao;

import com.zt.project.im.bean.Friend;
import com.zt.project.im.bean.vo.UserFriendVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ZhangTao
 * 2018/5/22 20:55
 * Description:
 */
@Mapper
public interface FriendDao {

    void add(Friend friend);

    void update(Friend friend);

    /**
     * 获取用户好友信息
     * @param userId
     * @return
     */
    List<UserFriendVO> getUserFriend(Integer userId);

}
