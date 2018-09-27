package com.byk.rong.system.mapper.read;

import com.byk.rong.common.mapper.read.BaseReadMapper;
import com.byk.rong.system.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleReadMapper extends BaseReadMapper<Role>{

    @Override
    Role selectById(String id);

    @Override
    List<Role> selectByParams(Role role);
}