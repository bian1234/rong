package com.byk.rong.system.mapper.read;

import com.byk.rong.common.mapper.read.BaseReadMapper;
import com.byk.rong.system.entity.RoleMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMenuReadMapper extends BaseReadMapper<RoleMenu>{

    @Override
    RoleMenu selectById(String id);

    @Override
    List<RoleMenu> selectByParams(RoleMenu roleMenu);
}