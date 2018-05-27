package com.zt.project.im.dao;

import com.zt.project.im.bean.User;
import com.zt.project.im.bean.vo.UserVO;
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

    /**
     * 获取用户信息
     * @param user
     * @return
     */
    UserVO getUserVOByUser(User user);

    /**
     * 获取用户信息
     * @param username
     * @return
     */
    UserVO getUserVO(@Param("username") String username);

    void addUserInfo(User user);

    /**
     * 添加用户
     * @param user
     */
    void addUser(User user);

    void delUserInfo(User user);
}
