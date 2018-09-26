package com.byk.rong.system.shiro;


import com.byk.rong.system.entity.User;
import com.byk.rong.system.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

/**
 * @Author: bianyakun
 * @Date: 2018/5/31 14:11
 * @Todo:
 */

public class MyShiroRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;

    /**
     * 认证信息(身份验证) Authentication 是用来验证用户身份   通过用户名在数据库中查找用户信息并返回
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 获取用户的输入帐号    从token鉴定类中获取当事人的信息，字面意思
        String username = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());
        // 通过username从数据库中查找 User对象，如果找到，没找到.
        // 实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        User userInfo = userService.findByUsername(username);
        // 账号不存在
        if (userInfo == null) {
            throw new UnknownAccountException("账号不正确");
        }

        // 密码错误
        if (!password.equals(userInfo.getPassword())) {
            throw new IncorrectCredentialsException("密码不正确");
        }
        // 参数分别为 用户信息，密码，盐值，真实名称
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userInfo, password, getName());
        return authenticationInfo;
    }


    /**
     * @Author: bianyakun
     * @Date: 2018/5/31 15:38
     * @todo:    根据用户信息获取角色及权限
     * @param:   当前登录的用户信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //获取当前登录用户信息
        User userInfo = (User) principals.getPrimaryPrincipal();
        //根据用户信息，获取其角色信息列表并遍历
        for(SysRole role:userInfo.getRoleIds()){
            //给用户赋予角色信息
            authorizationInfo.addRole(role.getRoleId());
            //根据角色信息获取权限
            for(SysMenu p:role.getMenuIds()){
                //增加权限信息
                authorizationInfo.addStringPermission(p.getMenuId());
            }
        }
        return authorizationInfo;
    }



    /**
     *@Author:      ykbian
     *@date_time:   2018/8/17 11:06
     *@Description:  这个也是获取权限信息======暂时理解不来
     *@param:
    */
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
//        String userId = ShiroUtils.getUserId();
//        MenuService menuService = ApplicationContextRegister.getBean(MenuService.class);
//        Set<String> perms = menuService.listPerms(userId);
//        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        info.setStringjuesms);
//        return info;
//    }
}
