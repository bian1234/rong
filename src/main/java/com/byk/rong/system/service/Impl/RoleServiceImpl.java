package com.byk.rong.system.service.Impl;

import com.byk.rong.system.entity.Role;
import com.byk.rong.system.mapper.read.RoleReadMapper;
import com.byk.rong.system.mapper.write.RoleWriteMapper;
import com.byk.rong.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return 0;
    }

    @Override
    public int updateSelective(Role role) {
        return 0;
    }

    @Override
    public Role selectById(String id) {
        return null;
    }

    @Override
    public List<Role> selectByParams(Role role) {
        return null;
    }

    @Override
    public int deleteById(String id) {
        return 0;
    }
}
