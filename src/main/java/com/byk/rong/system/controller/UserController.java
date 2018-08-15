package com.byk.rong.system.controller;

import com.byk.rong.common.controller.BaseController;
import com.byk.rong.system.entity.SysUser;
import com.byk.rong.system.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @Author: bianyakun
 * @Date: 2018/5/31 14:27
 * @todo: 用户信息控制
 * @param:   * @param null
 */
@Controller
@RequestMapping("sysUser")
public class UserController extends BaseController{


    @Autowired
    private UserService userService;

    /**
     * 用户查询.
     * @return
     */
    @RequestMapping("/userList")
    @RequiresPermissions("sysUser:view")//权限管理;
    public String sysUser(){
        return "sysUser";
    }

   /**
    *@Author:      ykbian
    *@date_time:   2018/8/15 17:22
    *@Description:  管理员添加用户==========
    *@param:
   */
   @ResponseBody
    @RequestMapping("/userAdd")
    //@RequiresPermissions("sysUser:add")//权限管理;
    public Map userInfoAdd(SysUser sysUser){
        if ( userService.save(sysUser) > 0){
            return insertSuccseeResponse();
        }
       return insertFailedResponse();
    }


    /**
     *@Author:      ykbian
     *@date_time:   2018/8/15 17:22
     *@Description:  自行注册用户信息
     *@param:
     */
    @ResponseBody
    @RequestMapping("/userRegist")
    public Map userRegist(SysUser sysUser){
        if ( userService.save(sysUser) > 0){
            return insertSuccseeResponse();
        }
        return insertFailedResponse();
    }



    /**
     * 用户删除;
     */
    @RequestMapping("/userDel")
    @RequiresPermissions("sysUser:del")//权限管理;
    public String userDel(){
        return "userInfoDel";
    }
}
