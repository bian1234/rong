package com.byk.rong.system.mapper.write;

import com.byk.rong.system.entity.Role;

public interface RoleWriteMapper {
    int deleteByPrimaryKey(String id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}