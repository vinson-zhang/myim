package com.zt.project.im.dao;

import com.zt.project.im.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author：ZhangTao
 * @Description:
 * @Date: Created in 13:07 2018/3/26
 */
@Mapper
public interface UserDao {


    /**
     * 获取用户信息
     * @param username
     * @return
     */
    User getUser(@Param("username") String username);

    void addUserInfo(User user);

    /**
     * 添加用户
     * @param user
     */
    void addUser(User user);

    void delUserInfo(User user);
}
