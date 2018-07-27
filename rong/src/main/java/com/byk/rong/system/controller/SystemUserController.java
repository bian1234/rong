package com.byk.rong.system.controller;

import com.byk.rong.commen.controller.BaseController;
import com.byk.rong.system.entity.SystemUser;
import com.byk.rong.system.service.SystemUserService;
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
    @PostMapping("/regist")
    public Map regist(SystemUser systemUser){
        System.out.println("==============="+systemUser);
        if (systemUserService.insertSelective(systemUser) > 0){
            return insertSuccseeResponse();
        }else {
            return  insertFailedResponse();
        }

    }
}
