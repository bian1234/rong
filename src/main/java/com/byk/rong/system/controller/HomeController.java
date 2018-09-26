package com.byk.rong.system.controller;

import com.byk.rong.common.controller.BaseController;
import com.byk.rong.system.entity.User;
import com.byk.rong.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: ykbian
 * @Date: 2018/9/26 15:55
 * @Todo:
 */
@Controller
public class HomeController   extends BaseController{


    @Autowired
    private UserService userService;
    @RequestMapping(value = {"/","","index"})
    public String index(){
        logger.info("有人访问主页");
        return "index";
    }

    /**
     * 简单测试数据访问
     */
    @ResponseBody
    @GetMapping("list")
    public void getUser(){
        User user0 = new User();
        user0.setId("f8f0062041cb47508a2ff2d33fc94fe5");
        List<User> users = userService.selectByParams(user0);
        System.out.println("++++++++++++"+users.get(0));
    }
}
