package com.zt.project.im.service.business.impl;

import com.zt.project.im.bean.Friend;
import com.zt.project.im.bean.vo.UserFriendVO;
import com.zt.project.im.dao.FriendDao;
import com.zt.project.im.enumpack.FriendStatusEnum;
import com.zt.project.im.service.business.IFriendService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * ZhangTao
 * 2018/5/22 20:08
 * Description:
 */
@Service
public class FriendServiceImpl implements IFriendService {

    private Logger logger = Logger.getLogger(FriendServiceImpl.class);

    @Autowired
    private FriendDao friendDao;

    @Override
    public void addFriend(Friend friend) {
        friend.setStatus(FriendStatusEnum.APPLYING.getStatus());
        friend.setCreateTime(new Date());
        friendDao.add(friend);
        //要增加推送内容

    }

    @Transactional
    @Override
    public void agreeFriend(Friend friend) {
        //更新关系
        friend.setStatus(FriendStatusEnum.AGREED.getStatus());
        friendDao.update(friend);
        //插入另一条记录
        Friend another = new Friend();
        another.setUserId(friend.getFriendId());
        another.setFriendId(friend.getUserId());
        another.setStatus(FriendStatusEnum.AGREED.getStatus());
        another.setCreateTime(new Date());
        friendDao.add(another);
    }

    @Override
    public List<UserFriendVO> getUserFriend(Integer userId) {
        List<UserFriendVO> userFriendVOList = friendDao.getUserFriend(userId);
        return userFriendVOList;
    }
}
