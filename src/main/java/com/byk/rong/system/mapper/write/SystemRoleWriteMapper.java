package com.byk.rong.system.mapper.write;

import com.byk.rong.system.entity.SystemRole;

public interface SystemRoleWriteMapper {
    int deleteByPrimaryKey(Long roleId);

    int insert(SystemRole record);

    int insertSelective(SystemRole record);

    SystemRole selectByPrimaryKey(Long roleId);

    int updateByPrimaryKeySelective(SystemRole record);

    int updateByPrimaryKey(SystemRole record);
}