package com.byk.rong.system.mapper.read;

import com.byk.rong.system.entity.SystemRole;

public interface SystemRoleReadMapper {
    int deleteByPrimaryKey(Long roleId);

    int insert(SystemRole record);

    int insertSelective(SystemRole record);

    SystemRole selectByPrimaryKey(Long roleId);

    int updateByPrimaryKeySelective(SystemRole record);

    int updateByPrimaryKey(SystemRole record);
}