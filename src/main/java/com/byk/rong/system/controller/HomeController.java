package com.byk.rong.system.controller;

import com.byk.rong.common.controller.BaseController;
import com.byk.rong.common.util.MD5Utils;
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
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/login")
    //@ResponseBody
    public String ajaxLogin(String username, String password) {
        String salt = userService.findByUsername(username).getSalt();
        password = MD5Utils.encrypt(username, password,salt);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        token.setRememberMe(true);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            return "index";
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return "login";
        }
    }
}
