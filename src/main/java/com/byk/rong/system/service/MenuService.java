package com.byk.rong.system.service;

import com.byk.rong.common.entity.Tree;
import com.byk.rong.system.entity.SysMenu;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author: ykbian
 * @Date: 2018/8/17 11:04
 * @Todo:   菜单的服务类
 */

public interface MenuService {
    
    /**
     * 通过id查询单个菜单树
     */
    Tree<SysMenu> getSysMenuTree(String id);


    /**
     *  通过id查询多个菜单树
     */
    List<Tree<SysMenu>> listMenuTree(String id);


    /**
     * 无参查询树形结构
     */
    Tree<SysMenu> getTree();



    /**
     * 有参查询
     */
    Tree<SysMenu> getTree(String id);

    List<SysMenu> list(Map<String, Object> params);

    int remove(String id);

    int save(SysMenu menu);

    int update(SysMenu menu);

    SysMenu get(String id);

    Set<String> listPerms(String userId);
}
