package com.byk.rong.system.mapper.read;

import com.byk.rong.system.entity.SysUserRole;

public interface SysUserRoleReadMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysUserRole record);

    int insertSelective(SysUserRole record);

    SysUserRole selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysUserRole record);

    int updateByPrimaryKey(SysUserRole record);
}