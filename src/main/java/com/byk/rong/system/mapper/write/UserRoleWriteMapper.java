package com.byk.rong.system.mapper.write;

import com.byk.rong.system.entity.UserRole;

public interface UserRoleWriteMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);
}