package com.byk.rong.system.mapper.write;

import com.byk.rong.system.entity.Menu;

public interface MenuWriteMapper {
    int deleteByPrimaryKey(String id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);
}