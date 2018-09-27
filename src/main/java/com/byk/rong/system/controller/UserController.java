package com.byk.rong.system.controller;

import com.byk.rong.common.config.BaseConstant;
import com.byk.rong.common.controller.BaseController;

import com.byk.rong.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.Map;

/**
 * @Author: ykbian
 * @Date: 2018/9/27 10:37
 * @Todo: 普通用户相关(修改信息，管理员增加用户，删除用户等)
 */

@Controller
@RequestMapping("/system/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;


    /**
     *@Author: ykbian
     *@date_time: 2018/9/27 14:51
     *@Description: 修改个人信息
     *@param:
     */


    /**
     *@Author: ykbian
     *@date_time: 2018/9/27 14:51
     *@Description: 管理员添加用户
     *@param:
     */


    /**
     *@Author: ykbian
     *@date_time: 2018/9/27 14:52
     *@Description: 删除用户信息
     *@param:
     */


}
