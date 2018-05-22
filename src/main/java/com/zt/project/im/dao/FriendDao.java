package com.zt.project.im.dao;

import com.zt.project.im.bean.Friend;
import org.apache.ibatis.annotations.Mapper;

/**
 * ZhangTao
 * 2018/5/22 20:55
 * Description:
 */
@Mapper
public interface FriendDao {

    void add(Friend friend);

    void update(Friend friend);

}
