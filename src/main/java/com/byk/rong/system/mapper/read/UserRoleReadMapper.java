package com.byk.rong.system.mapper.read;

import com.byk.rong.common.mapper.read.BaseReadMapper;
import com.byk.rong.system.entity.UserRole;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleReadMapper extends BaseReadMapper<UserRole>{

    @Override
    UserRole selectById(String id);

    @Override
    List<UserRole> selectByParams(UserRole userRole);
}