package com.byk.rong.system.mapper.read;

import com.byk.rong.common.mapper.read.BaseReadMapper;
import com.byk.rong.system.entity.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface MenuReadMapper extends BaseReadMapper<Menu>{

    @Override
    Menu selectById(String id);

    @Override
    List<Menu> selectByParams(Menu menu);

    /**
     *  带分页查询菜单列表
     */
    @Override
    List<Menu> list(Map<String, Object> params);


    /**
     *   根据用户id查询菜单列表（这个方法用到了关联查询）
     *   如果关联查询麻烦的话可以改为单表查询，根据userId 查询角色id  再根据角色id查询菜单id
     */
    List<Menu> listMenuByUserId(String id);

    List<String> listUserPerms(String id);
}