package com.zt.project.im.service.business.impl;

import com.zt.project.im.bean.Friend;
import com.zt.project.im.dao.FriendDao;
import com.zt.project.im.enumpack.FriendStatusEnum;
import com.zt.project.im.service.business.IFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * ZhangTao
 * 2018/5/22 20:08
 * Description:
 */
@Service
public class FriendServiceImpl implements IFriendService {

    @Autowired
    private FriendDao friendDao;

    @Override
    public void addFriend(Friend friend) {
        friend.setStatus(FriendStatusEnum.APPLYING.getStatus());
        friend.setCreateTime(new Date());
        friendDao.add(friend);
        //

    }
}
