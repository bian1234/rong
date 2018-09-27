package com.byk.rong.system.mapper.read;

import com.byk.rong.common.mapper.read.BaseReadMapper;
import com.byk.rong.system.entity.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuReadMapper extends BaseReadMapper<Menu>{

    @Override
    Menu selectById(String id);

    @Override
    List<Menu> selectByParams(Menu menu);
}