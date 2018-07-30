package com.byk.rong.system.controller;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author: ykbian
 * @Date: 2018/7/30 10:26
 * @Todo:   页面跳转控制
 */

@Controller
public class HomeController {

    @RequestMapping({"/","/index"})
    public String index(){
        return "index";
    }

    @RequestMapping(value="/login",method= RequestMethod.GET)
    public String login(){
        return "login";
    }


    @PostMapping("/toRegister")
    public String toRegister(){
        return "page-register";
    }

//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public String login(HttpServletRequest request, Map<String, Object> map) {
//        // 登录失败从request中获取shiro处理的异常信息
//        // shiroLoginFailure:就是shiro异常类的全类名
//        String exception = (String) request.getAttribute("shiroLoginFailure");
//        String msg = "";
//        if (exception != null) {
//            if (UnknownAccountException.class.getName().equals(exception)) {
//                msg = "UnknownAccountException -->帐号不存在：";
//            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
//                msg = "IncorrectCredentialsException -- > 密码不正确：";
//            } else if ("kaptchaValidateFailed".equals(exception)) {
//                msg = "kaptchaValidateFailed -- > 验证码错误";
//            } else {
//                msg = "else >> " + exception;
//                System.out.println("else -- >" + exception);
//            }
//        }
//        map.put("msg", msg);
//        // 此方法不处理登录成功,由shiro进行处理.
//        return "/login";
//    }
}
