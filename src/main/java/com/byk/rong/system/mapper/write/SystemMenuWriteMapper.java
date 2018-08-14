package com.byk.rong.system.mapper.write;

import com.byk.rong.system.entity.SystemMenu;

public interface SystemMenuWriteMapper {
    int deleteByPrimaryKey(Long menuId);

    int insert(SystemMenu record);

    int insertSelective(SystemMenu record);

    SystemMenu selectByPrimaryKey(Long menuId);

    int updateByPrimaryKeySelective(SystemMenu record);

    int updateByPrimaryKey(SystemMenu record);
}