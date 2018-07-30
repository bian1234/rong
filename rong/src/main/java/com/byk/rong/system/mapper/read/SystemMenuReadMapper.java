package com.byk.rong.system.mapper.read;

import com.byk.rong.system.entity.SystemMenu;

public interface SystemMenuReadMapper {
    int deleteByPrimaryKey(Long menuId);

    int insert(SystemMenu record);

    int insertSelective(SystemMenu record);

    SystemMenu selectByPrimaryKey(Long menuId);

    int updateByPrimaryKeySelective(SystemMenu record);

    int updateByPrimaryKey(SystemMenu record);
}