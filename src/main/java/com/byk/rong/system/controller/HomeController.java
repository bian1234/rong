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
     * 这个方法是跳转到登录页面
     */
    @GetMapping("/login")
    String login() {
        return "login";
    }

    @PostMapping("/login")
    //@ResponseBody
    public String ajaxLogin(String username, String password) {
        //根据用户名查询到盐值信息==================这要求用户名必须是唯一的。

            String  salt = userService.findByUsername(username).getSalt();
            //在MD5工具类里面，根据三个参数重新生成密码====这个规则是自己设定的，需要修改的话可以去MD5Utils。
            String password5 = MD5Utils.encryption(username,password,salt);

        //把用户名和密码存进token信息里面。========token信息会在MyShiroRealm类里面进行处理
        UsernamePasswordToken token = new UsernamePasswordToken(username, password5);
        System.out.println("/////"+token);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            System.out.println("=======================登录成功========================");
            return "index";
        } catch (Exception e) {
            System.out.println("=======================登录失败========================");
            e.printStackTrace();
            return "login";
        }
    }
}
