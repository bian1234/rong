package com.byk.rong.system.controller;

import com.byk.rong.common.config.BaseConstant;
import com.byk.rong.common.controller.BaseController;
import com.byk.rong.system.entity.Menu;
import com.byk.rong.system.entity.Role;
import com.byk.rong.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
        role.setDelFlag(BaseConstant.DEL_FLAG_USER);
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
        role.setDelFlag(BaseConstant.DEL_FLAG_DISUSER);
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
                role.setDelFlag(BaseConstant.DEL_FLAG_DISUSER);
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
    @GetMapping("menuList")
    @ResponseBody
    public Map menuList(@RequestParam Map<String, Object> map){
        List<Role> menus = roleService.list(map);
        if (menus.isEmpty()){
            return queryEmptyResponse();
        }return querySuccessResponse(menus);
    }
}
