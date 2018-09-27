package com.byk.rong.system.controller;

import com.byk.rong.common.config.BaseConstant;
import com.byk.rong.common.controller.BaseController;
import com.byk.rong.common.util.SaltUtil;
import com.byk.rong.system.entity.User;
import com.byk.rong.system.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: ykbian
 * @Date: 2018/9/26 15:55
 * @Todo:  系统用户相关（注册，访问主页，用户退出等）
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
    *@Author:      ykbian
    *@date_time:   2018/9/27 14:49
    *@Description: 用户登录
    *@param:
   */
    @RequestMapping("login")
    //@ResponseBody
    public String login(String userName,String password) {
        System.out.println("================================");
        System.out.println(userName);
        System.out.println(password);
        //添加用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userName,password);
        //进行验证，这里可以捕获异常，然后返回对应信息
        try {
            subject.login(usernamePasswordToken);
            System.out.println("登陆成功");
            return "index";
        }catch (UnknownAccountException e){
            System.out.println("账号错误");
        }catch (IncorrectCredentialsException e){
            System.out.println("密码错误");
        }catch (Exception e){
            e.printStackTrace();
        }

        return "login";
    }

    /**
     *@Author:      ykbian
     *@date_time:   2018/9/27 14:36
     *@Description: 用户退出
     *@param:
     */
    @PostMapping("logout")
    public void logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }

    /**
     *@Author:      ykbian
     *@date_time:   2018/9/27 10:38
     *@Description:  用户信息注册
     *@param:
     */
    @PostMapping("register")
    @ResponseBody
    public Map register(User user){
        // 将用户名作为盐值不安全，采用自定义的工具类
        String saltStr = SaltUtil.createSalt();
        ByteSource salt = ByteSource.Util.bytes(saltStr);
        /**
         * MD5加密：
         * 使用SimpleHash类对原始密码进行加密。
         * 第一个参数代表使用MD5方式加密
         * 第二个参数为原始密码
         * 第三个参数为盐值，即用户名
         * 第四个参数为加密次数
         * 最后用toHex()方法将加密后的密码转成String
         * */
        String newPs = new SimpleHash("MD5", user.getPassword(), salt, 1024).toHex();
        user.setSalt(saltStr);
        user.setPassword(newPs);
        user.setCreateTime(new Date());
        user.setDelFlag(BaseConstant.DEL_FLAG_USER);
        user.setStatus(BaseConstant.USER_NORMALITY);
        if (userService.insertSelective(user) > 0){
            return insertSuccseeResponse();
        }
        return insertFailedResponse();
    }
}