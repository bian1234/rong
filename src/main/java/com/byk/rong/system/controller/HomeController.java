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


}
