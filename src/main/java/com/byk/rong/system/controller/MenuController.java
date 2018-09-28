package com.byk.rong.system.controller;

import com.byk.rong.common.config.BaseConstant;
import com.byk.rong.common.controller.BaseController;
import com.byk.rong.system.entity.Menu;
import com.byk.rong.system.service.MenuService;
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
 * @Date: 2018/9/28 11:09
 * @Todo:   菜单控制
 */
@Controller
@RequestMapping("/system/menu")
public class MenuController  extends BaseController {

    @Autowired
    private MenuService menuService;


    /**
     *@Author:      ykbian
     *@date_time:   2018/9/28 11:10
     *@Description:  新增
     *@param:
    */
    @PostMapping("insert")
    @ResponseBody
    public Map insert(Menu menu) {
        menu.setCreateTime(new Date());
        menu.setCreateUser(getToken());
        menu.setDelFlag(BaseConstant.DEL_FLAG_USER);
        if (menuService.insertSelective(menu) > 0){
            return insertSuccseeResponse();
        }return insertFailedResponse();
    }
    /**
     *@Author:      ykbian
     *@date_time:   2018/9/28 11:11
     *@Description:  删除
     *@param:
    */
    @PostMapping("delete")
    @ResponseBody
    public Map delete(String id) {
        Menu menu = menuService.selectById(id);
        menu.setDelFlag(BaseConstant.DEL_FLAG_USER);
        menu.setDeleteTime(new Date());
        menu.setDeleteUser(getToken());
        if (menuService.updateSelective(menu) > 0){
            return deleteSuccessResponse();
        }return deleteFailedResponse();
    }

    /**
     *@Author:      ykbian
     *@date_time:   2018/9/28 11:11
     *@Description:  修改
     *@param:
    */


    /**
     *@Author:      ykbian
     *@date_time:   2018/9/28 11:11
     *@Description:  根据id查询单个菜单信息
     *@param:
    */
    @GetMapping("selectById")
    @ResponseBody
    public Map selectById(String id){
        Menu menu = menuService.selectById(id);
        if (menu == null){
            return queryEmptyResponse();
        }return querySuccessResponse(menu);
    }

    /**
     *@Author:      ykbian
     *@date_time:   2018/9/28 11:11
     *@Description:  批量查询所有的菜单信息
     *@param:
    */

    /**
     *@Author:      ykbian
     *@date_time:   2018/9/28 11:12
     *@Description:  批量删除菜单信息
     *@param:
    */
    @PostMapping("deleteBatch")
    @ResponseBody
    public Map deleteBatch(String[] ids) {
        for (String id:ids) {
            Menu menu = menuService.selectById(id);
            menu.setDelFlag(BaseConstant.DEL_FLAG_USER);
            menu.setDeleteTime(new Date());
            menu.setDeleteUser(getToken());
            menuService.updateSelective(menu);
        }
       return deleteSuccessResponse();
    }
}
