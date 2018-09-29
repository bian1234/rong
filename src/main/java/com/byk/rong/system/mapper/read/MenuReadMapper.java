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
    List<Menu> list(Map<String, Object> params);
}