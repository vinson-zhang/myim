package com.zt.project.im.relam;

import com.zt.project.im.bean.User;
import com.zt.project.im.service.IUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * ZhangTao
 * 2018/5/13 19:21
 * Description: 自定义realm-shiro
 */
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private IUserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        AuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取用户名
        String username = (String) authenticationToken.getPrincipal();
        if(username == null){
            return null;
        }
        User user = userService.getUser(username);
        if(user == null){
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username,user.getPassword(),getName());
        return authenticationInfo;
    }
}
