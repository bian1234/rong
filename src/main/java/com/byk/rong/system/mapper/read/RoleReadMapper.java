package com.byk.rong.system.mapper.read;

import com.byk.rong.system.entity.Role;

public interface RoleReadMapper {
    int deleteByPrimaryKey(String id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}