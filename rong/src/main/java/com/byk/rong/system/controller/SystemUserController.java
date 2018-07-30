package com.byk.rong.system.controller;

import com.byk.rong.commen.controller.BaseController;
import com.byk.rong.system.entity.SystemUser;
import com.byk.rong.system.service.SystemUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author: ykbian
 * @Date: 2018/7/27 15:57
 * @Todo:   系统用户管理控制
 */
@RestController
@RequestMapping("/systemUser")
public class SystemUserController extends BaseController{

    @Autowired
    private SystemUserService systemUserService;




    /**
     *@Author:      ykbian
     *@date_time:   2018/7/27 16:20
     *@Description:  注册账户
     *@param:
    */
    @PostMapping("/register")
    public Map register(SystemUser systemUser){
        if (systemUserService.insertSelective(systemUser) > 0){
            return insertSuccseeResponse();
        }else {
            return  insertFailedResponse();
        }

    }


    /**
     * 用户查询.
     * @return
     */
    @RequestMapping("/userList")
    @RequiresPermissions("userInfo:view")//权限管理;
    public String userInfo(){
        return "userInfo";
    }

    /**
     * 用户添加;
     * @return
     */
    @RequestMapping("/userAdd")
    @RequiresPermissions("userInfo:add")//权限管理;
    public String userInfoAdd(){
        return "userInfoAdd";
    }
    /**
     * 用户删除;
     */
    @RequestMapping("/userDel")
    @RequiresPermissions("userInfo:del")//权限管理;
    public String userDel(){
        return "userInfoDel";
    }
}
