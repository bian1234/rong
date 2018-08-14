package com.byk.rong.system.shrio;


import com.byk.rong.system.entity.SystemRole;
import com.byk.rong.system.entity.SystemUser;
import com.byk.rong.system.service.SystemUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @Author: bianyakun
 * @Date: 2018/5/31 14:11
 * @Todo:
 */

public class MyShiroRealm extends AuthorizingRealm{

    @Autowired
    private SystemUserService systemUserService;

    /**
     * 认证信息(身份验证) Authentication 是用来验证用户身份   通过用户名在数据库中查找用户信息并返回
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 获取用户的输入帐号    从token鉴定类中获取当事人的信息，字面意思
        String username = (String) token.getPrincipal();
        // 通过username从数据库中查找 User对象，如果找到，没找到.
        // 实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        SystemUser systemUser = systemUserService.findByUsername(username);
        if (systemUser == null) {
            return null;
        }
        // 参数分别为 用户信息，密码，盐值，真实名称
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(systemUser, systemUser.getPassword(),
                ByteSource.Util.bytes(systemUser.getSalt()), getName());
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
//        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        //获取当前登录用户信息
//        SystemUser systemUser = (SystemUser) principals.getPrimaryPrincipal();
//        //根据用户信息，获取其角色信息列表并遍历
//        for(SystemRole role:systemUser.getRoleList()){
//            //给用户赋予角色信息
//            authorizationInfo.addRole(role.getRole());
//            //根据角色信息获取权限
//            for(SysPermission p:role.getPermissions()){
//                //增加权限信息
//                authorizationInfo.addStringPermission(p.getPermission());
//            }
//        }
//        return authorizationInfo;
        return  null;
    }



}
