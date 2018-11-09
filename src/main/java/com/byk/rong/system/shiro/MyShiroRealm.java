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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
     *
     * 整体思路 ：
     *      1 、根据当前登录对象的id,在UserRole表中查询其角色信息，并将角色信息赋值给登陆对象；
     *      2 、遍历角色信息列表，根据RoleId在MenuRole表中查询菜单列表；
     *      3 、遍历菜单列表，将菜单中的权限信息存进Set集合；
     *      4 、 将含有权限信息的Set集合赋给当前登录对象并返回
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
       //创建返回对象
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        // SimpleAuthorizationInfo返回的是一个set集合
        Set<String> strings = new HashSet<>();
        // 从shiro中获取用户信息
        User user  = (User)principals.getPrimaryPrincipal();
       // 创建查询条件，根据用户信息获取角色信息
        UserRole userRole = new UserRole();
        userRole.setUserId(user.getId());
        List<UserRole> userRoles = userRoleService.selectByParams(userRole);
        //如果用户角色关系不为空
        if (!userRoles.isEmpty()){
            // 根据角色信息获取菜单权限
            for (UserRole userRole1:userRoles) {
                // 为登录用户赋角色信息
                authorizationInfo.addRole(roleService.selectById(userRole1.getRoleId()).getRoleName());
                //创建角色和菜单关系的查询条件
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setRoleId(userRole1.getRoleId());
                List<RoleMenu> roleMenus = roleMenuService.selectByParams(roleMenu);
                // 如果角色和菜单关系不为空
                if (!roleMenus.isEmpty()){
                    //遍历其中的菜单信息，将菜单中的权限信息组装成Set集合
                    for (RoleMenu roleMenu1:roleMenus) {
                        String perms = menuService.selectById(roleMenu1.getMenuId()).getPerms();
                        //只有当权限信息不为空并且不是空字符串时才有效
                        if (perms != null && !perms.equals("")){
                         strings.add(perms);
                        }
                    }
                }else {
                    return null;
                }
            }
        }else {
            return null;
        }
        //将获取到的菜单权限Set集合赋给当前登录对象
        authorizationInfo.setStringPermissions(strings);
        return authorizationInfo;
    }
}
