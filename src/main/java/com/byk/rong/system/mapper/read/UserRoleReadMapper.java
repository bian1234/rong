package com.byk.rong.system.mapper.read;

import com.byk.rong.system.entity.UserRole;

public interface UserRoleReadMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);
}