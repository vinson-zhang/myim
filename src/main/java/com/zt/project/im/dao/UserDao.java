package com.zt.project.im.dao;

import com.zt.project.im.bean.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author：ZhangTao
 * @Description:
 * @Date: Created in 13:07 2018/3/26
 */
@Mapper
public interface UserDao {

    List<User> findUserInfo();

    void addUserInfo(User user);

    void delUserInfo(User user);
}
