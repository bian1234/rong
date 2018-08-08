package com.byk.rong.system.controller;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author: bianyakun
 * @Date: 2018/5/31 11:23
 * @Todo:
 */

@Controller
public class HomeController {

    @RequestMapping(value = {"/","/index"},method = RequestMethod.GET)
    public String index(){
        return "index";
    }


    @RequestMapping(value = {"/main"})
    public String main(){
        return "main";
    }

//    @RequestMapping(value="/login",method= RequestMethod.GET)
//    public String login(){
//        return "login";
//    }
//
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
