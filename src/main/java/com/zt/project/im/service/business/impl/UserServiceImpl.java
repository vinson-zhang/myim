package com.zt.project.im.service.business.impl;

import com.zt.project.im.bean.User;
import com.zt.project.im.bean.vo.UserVO;
import com.zt.project.im.dao.UserDao;
import com.zt.project.im.service.business.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author：ZhangTao
 * @Description:
 * @Date: Created in 13:13 2018/3/26
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> getUserInfo() {
        List<User> users = new ArrayList<User>();
        User user = new User();
        user.setUsername("mike");
        user.setPassword("789");
        users.add(user);
//        return userDao.findUserInfo();
        return users;
    }

    @Override
    public void insert(User user) {
        userDao.addUserInfo(user);
    }

    @Override
    public void addUser(User user) {
        user.setCreateTime(new Date());
        userDao.addUser(user);
    }

    @Override
    public User getUser(String username) {
        return userDao.getUser(username);
    }

    @Override
    public UserVO getUserVO(String username) {
        UserVO userVO = userDao.getUserVO(username);
        return userVO;
    }
}
