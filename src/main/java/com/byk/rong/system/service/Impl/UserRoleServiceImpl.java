package com.byk.rong.system.service.Impl;

import com.byk.rong.system.entity.UserRole;
import com.byk.rong.system.mapper.read.UserRoleReadMapper;
import com.byk.rong.system.mapper.write.UserRoleWriteMapper;
import com.byk.rong.system.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: ykbian
 * @Date: 2018/9/27 13:56
 * @Todo:
 */
@Service
public class UserRoleServiceImpl  implements UserRoleService {

    @Autowired
    private UserRoleReadMapper userRoleReadMapper;
    @Autowired
    private UserRoleWriteMapper userRoleWriteMapper;

    @Override
    public int insertSelective(UserRole userRole) {
        return 0;
    }

    @Override
    public int updateSelective(UserRole userRole) {
        return 0;
    }

    @Override
    public UserRole selectById(String id) {
        return null;
    }

    @Override
    public List<UserRole> selectByParams(UserRole userRole) {
        return null;
    }

    @Override
    public int deleteById(String id) {
        return 0;
    }
}
