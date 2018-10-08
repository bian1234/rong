package com.byk.rong.system.service;

import com.byk.rong.common.service.BaseService;
import com.byk.rong.common.util.Tree;
import com.byk.rong.system.entity.Menu;

import java.util.List;
import java.util.Map;

/**
 * @Author: ykbian
 * @Date: 2018/9/27 14:15
 * @Todo:
 */

public interface MenuService  extends BaseService<Menu> {

    @Override
    int insertSelective(Menu menu);

    @Override
    int updateSelective(Menu menu);

    @Override
    Menu selectById(String id);

    @Override
    List<Menu> selectByParams(Menu menu);

    @Override
    int deleteById(String id);

    /**
     *  带分页查询菜单列表
     */
    @Override
    List<Menu> list(Map<String, Object> params);

    List<Tree<Menu>> listMenuTree(String id);

    Tree<Menu> getTree();

    Tree<Menu> getTree(String id);
}
