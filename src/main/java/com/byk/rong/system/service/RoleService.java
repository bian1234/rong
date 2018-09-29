package com.byk.rong.system.service;

import com.byk.rong.common.service.BaseService;
import com.byk.rong.system.entity.Role;
import com.byk.rong.system.mapper.read.RoleReadMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public interface RoleService  extends BaseService<Role> {


    @Override
    int insertSelective(Role role);

    @Override
    int updateSelective(Role role);

    @Override
    Role selectById(String id);

    @Override
    List<Role> selectByParams(Role role);

    @Override
    int deleteById(String id);

    @Override
    List<Role> list(Map<String, Object> params);
}
