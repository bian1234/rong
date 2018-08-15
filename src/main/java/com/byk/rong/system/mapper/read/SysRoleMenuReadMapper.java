package com.byk.rong.system.mapper.read;

import com.byk.rong.system.entity.SysRoleMenu;

public interface SysRoleMenuReadMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysRoleMenu record);

    int insertSelective(SysRoleMenu record);

    SysRoleMenu selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysRoleMenu record);

    int updateByPrimaryKey(SysRoleMenu record);
}