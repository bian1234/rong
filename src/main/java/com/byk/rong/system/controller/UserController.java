package com.byk.rong.system.controller;

import com.byk.rong.common.config.BaseConstant;
import com.byk.rong.common.controller.BaseController;

import com.byk.rong.common.util.SaltUtil;
import com.byk.rong.system.entity.User;
import com.byk.rong.system.service.UserService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
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
     * @Author: ykbian
     * @date_time: 2018/9/27 14:51
     * @Description: 修改个人信息
     * @param:
     */
    @ResponseBody
    @PostMapping("update")
    public Map update(User user) {
        user.setUpdateUser(getUserId());
        user.setUpdateTime(new Date());
        if (userService.updateSelective(user) > 0) {
            return updateSuccseeResponse();
        }
        return updateFailedResponse();
    }

    /**
     * @Author: ykbian
     * @date_time: 2018/9/27 14:51
     * @Description: 管理员添加用户
     * @param:
     */
    @ResponseBody
    @PostMapping("add")
    public Map add(User user) {
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
        if (userService.insertSelective(user) > 0) {
            return insertSuccseeResponse();
        }
        return insertFailedResponse();
    }

    /**
     * @Author: ykbian
     * @date_time: 2018/9/27 14:52
     * @Description: 管理员删除用户信息
     * @param:
     */
    @ResponseBody
    @PostMapping("delete")
    public Map delete(String id) {
        if (userService.deleteById(id) > 0) {
            return deleteSuccessResponse();
        }
        return deleteFailedResponse();
    }


    /**
     * @Author: ykbian
     * @date_time: 2018/10/15 10:55
     * @Description: 用户注销账户信息
     * @param:
     */
    @ResponseBody
    @PostMapping("cancel")
    public Map cancel(String id) {
        User user = userService.selectById(id);
        user.setDelFlag(BaseConstant.DEL_FLAG_DISUSER);
        user.setDeleteUser(getUserId());
        user.setDeleteTime(new Date());
        if (userService.updateSelective(user) > 0) {
            return deleteSuccessResponse();
        }
        return deleteFailedResponse();
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
        user.setCreateUser(BaseConstant.REGISTER_BY_ONESELF);
        if (userService.insertSelective(user) > 0){
            return insertSuccseeResponse();
        }

        return insertFailedResponse();
    }



    /**
     *@Author:      ykbian
     *@date_time:   2018/10/15 13:13
     *@Description:  查询个人信息
     *@param:
    */
    @ResponseBody
    @GetMapping("selectById")
    public Map selectById(String id) {
        User user = userService.selectById(id);
        if (user == null) {
            return queryEmptyResponse();
        }
        return querySuccessResponse(user);
    }

}
