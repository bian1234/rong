package com.byk.rong.system.controller;

import com.byk.rong.common.controller.BaseController;
import com.byk.rong.common.util.MD5Utils;
import com.byk.rong.system.entity.SysUser;
import com.byk.rong.system.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author: ykbian
 * @Date: 2018/8/15 13:01
 * @Todo:   页面跳转控制
 */
@Controller
public class HomeController extends BaseController{

    @Autowired
    private UserService userService;

    @RequestMapping({"/","/index"})
    public String index(){
        return "index";
    }

    /**
     * 跳转登录界面
     */
    @RequestMapping(value="/login",method= RequestMethod.GET)
    public String login(){
        return "login";
    }


    @RequestMapping("/register")
    public String toRegister(){
        System.out.println("==========跳转注册界面=========");
        return "register";
    }

    @PostMapping("/login")
    //@ResponseBody
    public String ajaxLogin(String username, String password, Model model) {
        SysUser sysUser  = userService.findByUsername(username);
        if (sysUser == null){
            model.addAttribute("msg", "* 用户名或者密码错误");
            return "login";
        }
        String salt = sysUser.getSalt();
        password = MD5Utils.encrypt(username, password,salt);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            return "index";
        } catch (AuthenticationException e) {
            model.addAttribute("msg", "* 用户名或者密码错误");
            return "login";
        }
    }
}
