package com.byk.rong.system.controller;

import com.byk.rong.common.config.Constant;
import com.byk.rong.common.controller.BaseController;
import com.byk.rong.common.util.Tree;
import com.byk.rong.system.entity.Dept;
import com.byk.rong.system.entity.Menu;
import com.byk.rong.system.service.DeptService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.ibatis.annotations.Param;
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
 * @Date: 2018/10/25 9:25
 * @Todo:
 */
@Controller
@RequestMapping("/system/dept")
public class DeptController  extends BaseController {

    @Autowired
    private DeptService deptService;

    /**
     *@Author:      ykbian
     *@date_time:   2018/10/25 9:27
     *@Description: 树形结构遍历部门信息
     *@param:
    */
    @GetMapping("list")
    @ResponseBody
    @RequiresPermissions("system:dept:dept")
    public List<Dept> list(@RequestParam Map<String, Object> map){
        List<Dept> depts = deptService.list(map);
        if (depts.isEmpty()){
            return null;
        }
        return depts;
    }

    /**
     *@Author:      ykbian
     *@date_time:   2018/10/25 9:27
     *@Description: 新增部门信息
     *@param:
    */
    @PostMapping("insert")
    @ResponseBody
    public Map insert(Dept dept){
        // 演示账户是不能新增的
        if (Constant.DEMO_ACCOUNT_ID.equals(getUserId())) {
            logger.info("演示账户是不能新增信息的");
            return demoAccount();
        }
        if (deptService.insertSelective(dept) > 0){
            return insertSuccseeResponse();
        }
        return insertFailedResponse();
    }

    /**
     *@Author:      ykbian
     *@date_time:   2018/10/25 9:27
     *@Description: 删除部门信息
     *@param:
    */
    @PostMapping("delete")
    @ResponseBody
    public Map delete(String id){
        Dept dept = deptService.selectById(id);
        if (dept == null){
            return deleteFailedResponse();
        }
        dept.setDeleteUser(getUserId());
        dept.setDeleteTime(new Date());
        dept.setDelFlag(Constant.DEL_FLAG_DISUSER);
        if (deptService.updateSelective(dept) < 1){
            return deleteFailedResponse();
        }
        return deleteSuccessResponse();
    }

    /**
     *@Author:      ykbian
     *@date_time:   2018/10/25 9:28
     *@Description: 修改部门信息
     *@param:
    */
    @PostMapping("update")
    @ResponseBody
    public Map update(Dept dept){
        dept.setUpdateTime(new Date());
        dept.setUpdateUser(getUserId());
        if (deptService.updateSelective(dept) > 0){
            return updateSuccseeResponse();
        }
        return updateFailedResponse();
    }

    /**
     *@Author:      ykbian
     *@date_time:   2018/10/25 9:31
     *@Description:  跳转部门列表界面
     *@param:
    */
    @GetMapping()
    @RequiresPermissions("system:dept:dept")
    public String dept(){
        return "/system/dept/dept";
    }
    /**
     *@Author:      ykbian
     *@date_time:   2018/10/25 9:28
     *@Description:  跳转新增界面
     *@param:
    */
    @GetMapping("add/{pId}")
    public String add(Model model, @PathVariable("pId")String pId){
        //父节点
        model.addAttribute("pId", pId);
        // 查找父菜单的名称
        if (Constant.TOP_DEPT_PARIENT_ID.equals(pId)) {
            model.addAttribute("pName", Constant.TOP_MENU_NAME);
        } else {
            model.addAttribute("pName", deptService.selectById(pId).getDeptName());
        }
        return "/system/dept/add";
    }

    /**
     *@Author:      ykbian
     *@date_time:   2018/10/25 9:28
     *@Description: 跳转编辑界面
     *@param:
    */
    @GetMapping("edit/{id}")
    public String edit(Model model, @PathVariable("id")String id){
        Dept dept = deptService.selectById(id);
        String pId = dept.getParentId();
        model.addAttribute("pId", pId);
        if (pId.equals(Constant.TOP_DEPT_PARIENT_ID)) {
            model.addAttribute("pName", Constant.TOP_DEPT_NAME);
        } else {
            model.addAttribute("pName", deptService.selectById(pId).getDeptName());
        }
        model.addAttribute("dept", dept);
        return "/system/dept/edit";
    }
}
