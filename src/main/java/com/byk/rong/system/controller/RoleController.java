package com.byk.rong.system.controller;

import com.byk.rong.common.config.Constant;
import com.byk.rong.common.controller.BaseController;
import com.byk.rong.system.entity.Role;
import com.byk.rong.system.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: ykbian
 * @Date: 2018/9/29 14:40
 * @Todo:   角色
 */
@Controller
@RequestMapping("/system/role")
public class RoleController  extends BaseController {

    @Autowired
    private RoleService roleService;



    /**
     *@Author:      ykbian
     *@date_time:   2018/9/29 14:41
     *@Description: 增加角色信息
     *@param:
    */
    @ResponseBody
    @PostMapping("insert")
    public Map insert(Role role){
        role.setCreateTime(new Date());
        role.setCreateUser(getUserId());
        role.setDelFlag(Constant.DEL_FLAG_USER);
        if (roleService.insertSelective(role) > 0){
            return insertSuccseeResponse();
        }
        return  insertFailedResponse();
    }


    /**
     *@Author:      ykbian
     *@date_time:   2018/9/29 14:49
     *@Description:  删除角色信息
     *@param:
    */
    @PostMapping("delete")
    @ResponseBody
    public Map delete(String id){
        Role role = roleService.selectById(id);
        if (role == null){
            return  deleteFailedResponse();
        }
        // 如果是超级管理员的角色信息，不能删除
        if (Constant.ROOT_ROLE_ID.equals(id)){
            logger.info("有人企图删除超级管理员的角色，但是被我给拒绝了！");
            return deleteFailedResponse();
        }
        role.setDelFlag(Constant.DEL_FLAG_DISUSER);
        role.setDeleteTime(new Date());
        role.setDeleteUser(getUserId());
        if (roleService.updateSelective(role) < 1){
            return deleteFailedResponse();
        }
        return deleteSuccessResponse();
    }



    /**
     *@Author:      ykbian
     *@date_time:   2018/9/29 14:54
     *@Description: 删除多个角色信息
     *@param:
    */
    @PostMapping("deleteBatch")
    @ResponseBody
    public Map deleteBatch(@RequestParam("ids[]") String[] ids) {
       /**
        *   判断，演示账户的话拒绝删除
        */
        for (String id:ids) {
            // 这里可能会引发空指针异常
            try {
                Role role = roleService.selectById(id);
                // 如果是超级管理员的角色信息，不能删除
                if (Constant.ROOT_ROLE_ID.equals(id)){
                    logger.info("有人企图删除超级管理员的角色，但是被我给拒绝了！");
                    continue;
                }
                role.setDelFlag(Constant.DEL_FLAG_DISUSER);
                role.setDeleteTime(new Date());
                role.setDeleteUser(getUserId());
                roleService.updateSelective(role);
            }catch (Exception e){
                logger.info("批量删除时，出现了空的id:"+id);
            }
        }
        return deleteSuccessResponse();
    }


    /**
     *@Author:      ykbian
     *@date_time:   2018/9/29 14:49
     *@Description:  更新角色信息
     *@param:
    */
    @ResponseBody
    @PostMapping("update")
    public Map update(Role role){
        role.setUpdateTime(new Date());
        role.setUpdateUser(getUserId());
        if (roleService.updateSelective(role) > 0){
            return updateSuccseeResponse();
        }
        return updateFailedResponse();
    }

    /**
     *@Author:      ykbian
     *@date_time:   2018/9/29 14:50
     *@Description:  查询单个角色信息
     *@param:
    */
    @ResponseBody
    @GetMapping("selectById")
    public Map selectById(String id){
        Role role = roleService.selectById(id);
        if (role ==null){
            return queryEmptyResponse();
        }
        return querySuccessResponse(role);
    }


    /**
     *@Author:      ykbian
     *@date_time:   2018/9/29 14:50
     *@Description:  查询角色列表
     *@param:
    */
    @GetMapping("/list")
    @ResponseBody
    @RequiresPermissions("system:role:role")
    public List<Role> menuList(@RequestParam Map<String, Object> map){
        List<Role> roles = roleService.list(map);
       return roles;
    }


    /**
     *@Author:      ykbian
     *@date_time:   2018/10/25 14:05
     *@Description:  跳转角色管理界面
     *@param:
    */
    @GetMapping()
    @RequiresPermissions("system:role:role")
    public String list(){
        return "/system/role/role";
    }

    /**
     *@Author:      ykbian
     *@date_time:   2018/10/25 14:06
     *@Description: 跳转角色增加界面
     *@param:
    */
    @GetMapping("add")
    public String  add(){
        return "/system/role/add";
    }

    /**
     *@Author:      ykbian
     *@date_time:   2018/10/25 14:06
     *@Description:  跳转角色修改界面
     *@param:
    */
    @GetMapping("edit/{id}")
    public String edit(@PathVariable("id") String id, Model model){
        Role role = roleService.selectById(id);
        model.addAttribute("role",role);
        return "/system/role/edit";
    }
}
