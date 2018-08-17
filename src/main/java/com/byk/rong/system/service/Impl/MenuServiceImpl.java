package com.byk.rong.system.service.Impl;

import com.byk.rong.common.entity.Tree;
import com.byk.rong.common.util.BuildTree;
import com.byk.rong.system.entity.SysMenu;
import com.byk.rong.system.mapper.read.SysMenuReadMapper;
import com.byk.rong.system.mapper.read.SysRoleMenuReadMapper;
import com.byk.rong.system.mapper.write.SysMenuWriteMapper;
import com.byk.rong.system.mapper.write.SysRoleMenuWriteMapper;
import com.byk.rong.system.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.StringUtils;
import java.util.*;

/**
 * @Author: ykbian
 * @Date: 2018/8/17 11:44
 * @Todo:
 */
@Service
@Transactional(readOnly = true,rollbackFor = Exception.class)
public class MenuServiceImpl  implements MenuService {

    
  
    @Autowired
    private SysMenuReadMapper sysMenuReadMapper;
    @Autowired
    private SysMenuWriteMapper sysMenuWriteMapper;
    
    @Autowired
    private SysRoleMenuReadMapper sysRoleMenuReadMapper;


    @Autowired
    private SysRoleMenuWriteMapper roleMenuWriteMapper;

    /**
     * @param
     * @return 树形菜单
     */
    @Cacheable
    @Override
    public Tree<SysMenu> getSysMenuTree(String id) {
        List<Tree<SysMenu>> trees = new ArrayList<Tree<SysMenu>>();
        List<SysMenu> menuDOs = sysMenuReadMapper.listMenuByUserId(id);
        for (SysMenu sysMenuDO : menuDOs) {
            Tree<SysMenu> tree = new Tree<SysMenu>();
            tree.setId(sysMenuDO.getMenuId().toString());
            tree.setParentId(sysMenuDO.getParentId().toString());
            tree.setText(sysMenuDO.getName());
            Map<String, Object> attributes = new HashMap<>(16);
            attributes.put("url", sysMenuDO.getUrl());
            attributes.put("icon", sysMenuDO.getIcon());
            tree.setAttributes(attributes);
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        Tree<SysMenu> t = BuildTree.build(trees);
        return t;
    }

    @Override
    public List<SysMenu> list(Map<String, Object> params) {
        List<SysMenu> menus = sysMenuReadMapper.list(params);
        return menus;
    }

    @Transactional(readOnly = false,rollbackFor = Exception.class)
    @Override
    public int remove(String id) {
        int result = sysMenuWriteMapper.remove(id);
        return result;
    }
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    @Override
    public int save(SysMenu menu) {
        int r = sysMenuWriteMapper.save(menu);
        return r;
    }

    @Transactional(readOnly = false,rollbackFor = Exception.class)
    @Override
    public int update(SysMenu menu) {
        int r = sysMenuWriteMapper.update(menu);
        return r;
    }

    @Override
    public SysMenu get(String id) {
        SysMenu menuDO = sysMenuReadMapper.get(id);
        return menuDO;
    }

    @Override
    public Tree<SysMenu> getTree() {
        List<Tree<SysMenu>> trees = new ArrayList<Tree<SysMenu>>();
        List<SysMenu> menuDOs = sysMenuReadMapper.list(new HashMap<>(16));
        for (SysMenu sysMenuDO : menuDOs) {
            Tree<SysMenu> tree = new Tree<SysMenu>();
            tree.setId(sysMenuDO.getMenuId().toString());
            tree.setParentId(sysMenuDO.getParentId().toString());
            tree.setText(sysMenuDO.getName());
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        Tree<SysMenu> t = BuildTree.build(trees);
        return t;
    }

    @Override
    public Tree<SysMenu> getTree(String id) {
        // 根据roleId查询权限
        List<SysMenu> menus = sysMenuReadMapper.list(new HashMap<String, Object>(16));
        List<String> menuIds = sysRoleMenuReadMapper.listMenuIdByRoleId(id);
        List<String> temp = menuIds;
        for (SysMenu menu : menus) {
            if (temp.contains(menu.getParentId())) {
                menuIds.remove(menu.getParentId());
            }
        }
        List<Tree<SysMenu>> trees = new ArrayList<Tree<SysMenu>>();
        List<SysMenu> menuDOs = sysMenuReadMapper.list(new HashMap<String, Object>(16));
        for (SysMenu sysMenuDO : menuDOs) {
            Tree<SysMenu> tree = new Tree<SysMenu>();
            tree.setId(sysMenuDO.getMenuId().toString());
            tree.setParentId(sysMenuDO.getParentId().toString());
            tree.setText(sysMenuDO.getName());
            Map<String, Object> state = new HashMap<>(16);
            String menuId = sysMenuDO.getMenuId();
            if (menuIds.contains(menuId)) {
                state.put("selected", true);
            } else {
                state.put("selected", false);
            }
            tree.setState(state);
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        Tree<SysMenu> t = BuildTree.build(trees);
        return t;
    }

    @Override
    public Set<String> listPerms(String userId) {
        List<String> perms = sysMenuReadMapper.listUserPerms(userId);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms) {
            if (StringUtils.isNotBlank(perm)) {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }

    @Override
    public List<Tree<SysMenu>> listMenuTree(String id) {
        List<Tree<SysMenu>> trees = new ArrayList<Tree<SysMenu>>();
        List<SysMenu> menuDOs = sysMenuReadMapper.listMenuByUserId(id);
        for (SysMenu sysMenuDO : menuDOs) {
            Tree<SysMenu> tree = new Tree<SysMenu>();
            tree.setId(sysMenuDO.getMenuId().toString());
            tree.setParentId(sysMenuDO.getParentId().toString());
            tree.setText(sysMenuDO.getName());
            Map<String, Object> attributes = new HashMap<>(16);
            attributes.put("url", sysMenuDO.getUrl());
            attributes.put("icon", sysMenuDO.getIcon());
            tree.setAttributes(attributes);
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        List<Tree<SysMenu>> list = BuildTree.buildList(trees, "0");
        return list;
    }
}
