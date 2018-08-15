package com.byk.rong.system.mapper.read;

import com.byk.rong.system.entity.SysMenu;

public interface SysMenuReadMapper {
    int deleteByPrimaryKey(String menuId);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    SysMenu selectByPrimaryKey(String menuId);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);
}