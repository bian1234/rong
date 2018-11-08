package com.byk.rong.system.shiro;


import com.byk.rong.common.config.Constant;
import com.byk.rong.system.entity.*;
import com.byk.rong.system.service.*;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author: bianyakun
 * @Date: 2018/5/31 14:11
 * @Todo:
 */

public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RoleMenuService roleMenuService;
    @Autowired
    private MenuService menuService;

    /**
     * 认证信息(身份验证) Authentication 是用来验证用户身份   通过用户名在数据库中查找用户信息并返回
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 获取token中的用户名和密码
        String username = (String) token.getPrincipal();
        // 实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        //数据库内用户的用户名必须是唯一的
        User userInfo = userService.findByUsername(username);
        // 账号不存在
        if (userInfo == null) {
            throw new UnknownAccountException("账号不正确~~~~~~~~~~");
        }
        // 账号锁定
        if (userInfo.getStatus() == Constant.USER_DISABLE) {
            throw new LockedAccountException("账号已被锁定,请联系管理员");
        }
        ByteSource salt = ByteSource.Util.bytes(userInfo.getSalt());
        /**
         *    注意：此处四个参数分别是用户实体信息，数据库查询到的密码，盐值和当前Realm的名字。
         *    尤其要注意第一个参数，必须是用户实体信息不能是用户名。网上很多demo写的是用户名，
         *    虽然登录功能实现，但是后面取出当前登录对象信息会报错，这里存的是用户名，取出来的就是
         *    用户名，这里存的是用户信息，取出来的就是用户信息。
         *
         *    参考资料：https://blog.csdn.net/weixin_42456466/article/details/80744371
         */
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userInfo,
                userInfo.getPassword(), //密码
                salt,
                getName());
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
        // 从shiro中获取用户信息
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User user  = (User)principals.getPrimaryPrincipal();
        // 根据用户信息获取角色信息
        UserRole userRole = new UserRole();
                // 角色和用户关系表
        List<UserRole> userRoles = userRoleService.selectByParams(userRole);
        if (!userRoles.isEmpty()){
            //遍历出角色信息
            for (UserRole userRole1:userRoles) {
                Role role = roleService.selectById(userRole1.getRoleId());
                // 将角色信息赋给shrio=========赋的是角色名不是id
                authorizationInfo.addRole(role.getRoleName());
                // 根据角色id查询权限信息
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setRoleId(role.getId());
                List<RoleMenu> roleMenus = roleMenuService.selectByParams(roleMenu);
                if (!roleMenus.isEmpty()){
                    for (RoleMenu roleMenu1:roleMenus) {
                       Menu menu =  menuService.selectById(roleMenu1.getMenuId());
                       // 将权限名称赋给当前用户
                        authorizationInfo.addStringPermission(menu.getPerms());
                    }
                }
            }
        }
        return authorizationInfo;
    }




}
