package com.byk.rong.system.service.Impl;

import com.byk.rong.system.entity.Role;
import com.byk.rong.system.mapper.read.RoleReadMapper;
import com.byk.rong.system.mapper.write.RoleWriteMapper;
import com.byk.rong.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: ykbian
 * @Date: 2018/9/27 13:51
 * @Todo:
 */
@Service
public class RoleServiceImpl  implements RoleService {


    @Autowired
    private RoleReadMapper roleReadMapper;
    @Autowired
    private RoleWriteMapper roleWriteMapper;
    @Override
    public int insertSelective(Role role) {
        return roleWriteMapper.insertSelective(role);
    }

    @Override
    public int updateSelective(Role role) {
        return roleWriteMapper.updateSelective(role);
    }

    @Override
    public Role selectById(String id) {
        return roleReadMapper.selectById(id);
    }

    @Override
    public List<Role> selectByParams(Role role) {
        return null;
    }

    @Override
    public int deleteById(String id) {
        return 0;
    }

    @Override
    public List<Role> list(Map<String, Object> params) {
        return roleReadMapper.list(params);
    }
}
