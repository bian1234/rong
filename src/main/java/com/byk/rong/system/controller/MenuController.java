package com.byk.rong.system.controller;

import com.byk.rong.common.annotation.Log;
import com.byk.rong.common.config.BaseConstant;
import com.byk.rong.common.controller.BaseController;
import com.byk.rong.common.util.Tree;
import com.byk.rong.system.entity.Menu;
import com.byk.rong.system.entity.Role;
import com.byk.rong.system.service.MenuService;
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
        menu.setCreateUser(getUserId());
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
        if (menu == null){
            return deleteFailedResponse();
        }
        menu.setDelFlag(BaseConstant.DEL_FLAG_DISUSER);
        menu.setDeleteTime(new Date());
        menu.setDeleteUser(getUserId());
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
    @ResponseBody
    @PostMapping("update")
    public Map update(Menu menu){
        /**
         *  如果这里传入的菜单信息不存在，也就是根据id找不到这数据，直接返回失败。
         *  但是，如果数据已经删除，也就是不存在的话，前端获取不到这条数据的信息，也就不会传入这个数据的id，因为不用查询校验。
         */
        if (menuService.selectById(menu.getId()) == null){
            return updateFailedResponse();
        }
        menu.setUpdateTime(new Date());
        menu.setUpdateUser(getUserId());
        if (menuService.updateSelective(menu) > 0){
            return updateSuccseeResponse();
        }
        return updateFailedResponse();
    }

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
     *@date_time:   2018/9/28 11:12
     *@Description:  批量删除菜单信息
     *@param:
    */
    @PostMapping("deleteBatch")
    @ResponseBody
    public Map deleteBatch(String[] ids) {
        for (String id:ids) {
            Menu menu = menuService.selectById(id);
            menu.setDelFlag(BaseConstant.DEL_FLAG_DISUSER);
            menu.setDeleteTime(new Date());
            menu.setDeleteUser(getUserId());
            menuService.updateSelective(menu);
        }
       return deleteSuccessResponse();
    }

    /**
     *@Author:      ykbian
     *@date_time:   2018/9/28 11:11
     *@Description:  批量查询所有的菜单信息=======简单的列表查询，
     *               这个方法的返回值是List<Menu>而不是Map，因为前端使用了bootstrapTreeTable技术。List数据结构更好操作。
     *@param:
     */
    @GetMapping("list")
    @ResponseBody
    public List<Menu> list(@RequestParam Map<String, Object> map){
        List<Menu> menus = menuService.list(map);
        if (menus.isEmpty()){
            return null;
        }
        return menus;
    }


    /**
     *@Author:      ykbian
     *@date_time:   2018/10/9 13:05
     *@Description: 跳转系统菜单页面
     *@param:
    */
    @GetMapping("")
    public  String  listAllMenu() {
       return "system/menu/menu";
    }

    /**
     *@Author:      ykbian
     *@date_time:   2018/10/9 15:43
     *@Description: 跳转增加页面:查询到父菜单的信息，进行操作
     *@param:
    */
    @GetMapping("add/{pId}")
    public  String  add(Model model, @PathVariable("pId") String pId) {
        //父节点
        model.addAttribute("pId", pId);
        // 查找父菜单的名称
        if (BaseConstant.TOP_MENU_PARIENT_ID.equals(pId)) {
            model.addAttribute("pName", "根目录");
        } else {
            model.addAttribute("pName", menuService.selectById(pId).getName());
        }
        return "system/menu/add";
    }


    /**
     *@Author:      ykbian
     *@date_time:   2018/10/9 15:43
     *@Description:  跳转编辑界面
     *@param:
    */
    @GetMapping("edit/{id}")
    public  String  edit(Model model, @PathVariable("id") String id) {
        Menu menu = menuService.selectById(id);
        String pId = menu.getParentId();
        model.addAttribute("pId", pId);
        // 如果pid是“0”的话，则为一级菜单，其父菜单为"根目录"
        if (BaseConstant.TOP_MENU_PARIENT_ID.equals(pId)) {
            model.addAttribute("pName", "根目录");
        } else {
            model.addAttribute("pName", menuService.selectById(pId).getName());
        }
        model.addAttribute("menu", menu);
        return "system/menu/edit";
    }

    /**
     *@Author:      ykbian
     *@date_time:   2018/9/29 13:06
     *@Description:  查询所有的菜单信息=========左侧栏树形结构展示
     *@param:
    */
    @GetMapping("/tree")
    @ResponseBody
    public  Map  tree() {
        Tree<Menu> tree = menuService.getTree();
        if (tree == null){
            return queryEmptyResponse();
        }
        return querySuccessResponse(tree);
    }

    /**
     *@Author:      ykbian
     *@date_time:   2018/10/9 14:32
     *@Description:  根据userId查询响应的菜单信息=========左侧栏树形结构展示
     *@param:
    */
    @GetMapping("/tree/{roleId}")
    @ResponseBody
    Tree<Menu> tree(@PathVariable("userId") String userId) {
        Tree<Menu> tree = menuService.getTree(userId);
        return tree;
    }
}
