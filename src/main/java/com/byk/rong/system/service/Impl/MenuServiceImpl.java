package com.byk.rong.system.service.Impl;

import com.byk.rong.common.util.BuildTree;
import com.byk.rong.common.util.Tree;
import com.byk.rong.system.entity.Menu;
import com.byk.rong.system.mapper.read.MenuReadMapper;
import com.byk.rong.system.mapper.write.MenuWriteMapper;
import com.byk.rong.system.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: ykbian
 * @Date: 2018/9/27 14:17
 * @Todo:
 */
@Service
public class MenuServiceImpl  implements MenuService {

    @Autowired
    private MenuReadMapper menuReadMapper;
    @Autowired
    private MenuWriteMapper menuWriteMapper;
    @Override
    public int insertSelective(Menu menu) {
        return menuWriteMapper.insertSelective(menu);
    }

    @Override
    public int updateSelective(Menu menu) {
        return menuWriteMapper.updateSelective(menu);
    }

    @Override
    public Menu selectById(String id) {
        return menuReadMapper.selectById(id);
    }

    @Override
    public List<Menu> selectByParams(Menu menu) {
        return menuReadMapper.selectByParams(menu);
    }

    @Override
    public int deleteById(String id) {
        return 0;
    }

    @Override
    public List<Menu> list(Map<String, Object> params) {
        return menuReadMapper.list(params);
    }

    @Override
    public Tree<Menu> getTree() {
        // 创建返回对象
        List<Tree<Menu>> trees = new ArrayList<Tree<Menu>>();
        // 查询出所有的菜单信息
        List<Menu> menuDOs = menuReadMapper.list(new HashMap<>(16));
        // 遍历菜单信息
        for (Menu sysMenuDO : menuDOs) {
            // 创建树形结构
            Tree<Menu> tree = new Tree<Menu>();
            // 树形支点的id是菜单的id
            tree.setId(sysMenuDO.getId());
            tree.setParentId(sysMenuDO.getParentId());
            tree.setText(sysMenuDO.getName());
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        Tree<Menu> t = BuildTree.build(trees);
        return t;
    }

    @Override
    public Tree<Menu> getTree(String id) {
        return null;
    }
}
