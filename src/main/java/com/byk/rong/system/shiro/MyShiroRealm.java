package com.byk.rong.system.shiro;


import com.byk.rong.system.entity.SysUser;
import com.byk.rong.system.service.UserService;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

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

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(sysUser, sysUser.getPassword(),
                ByteSource.Util.bytes(sysUser.getSalt()), getName());
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
        SysUser userInfo = (SysUser) principals.getPrimaryPrincipal();
        //根据用户信息，获取其角色信息列表并遍历
//        for(SysRole role:userInfo.getRoleList()){
//            //给用户赋予角色信息
//            authorizationInfo.addRole(role.getRole());
//            //根据角色信息获取权限
//            for(SysPermission p:role.getPermissions()){
//                //增加权限信息
//                authorizationInfo.addStringPermission(p.getPermission());
//            }
//        }
        return authorizationInfo;
    }

}
