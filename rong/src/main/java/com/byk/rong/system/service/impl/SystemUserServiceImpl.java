package com.byk.rong.system.service.impl;

import com.byk.rong.commen.util.MD5Util;
import com.byk.rong.commen.util.SaltUtil;
import com.byk.rong.system.entity.SystemUser;
import com.byk.rong.system.mapper.read.SystemUserReadMapper;
import com.byk.rong.system.mapper.write.SystemUserWriteMapper;
import com.byk.rong.system.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: ykbian
 * @Date: 2018/7/27 16:12
 * @Todo:
 */
@Service
public class SystemUserServiceImpl  implements SystemUserService {

    @Autowired
    private SystemUserReadMapper systemUserReadMapper;
    @Autowired
    private SystemUserWriteMapper systemUserWriteMapper;

    @Autowired
    private SaltUtil saltUtil;

    @Autowired
    private MD5Util md5Util;

    @Override
    public int insertSelective(SystemUser systemUser) {
        String salt = saltUtil.getSlat(systemUser.getName());
        String passWord = md5Util.getMD5(systemUser.getPassword()+salt);
        systemUser.setSalt(salt);
        systemUser.setPassword(passWord);
        return systemUserWriteMapper.insertSelective(systemUser);
    }

    @Override
    public int updateSelective(SystemUser systemUser) {
        return systemUserWriteMapper.updateSelective(systemUser);
    }

    @Override
    public SystemUser selectById(String id) {
        return systemUserReadMapper.selectById(id);
    }

    @Override
    public List<SystemUser> selectByParams(SystemUser systemUser) {
        return systemUserReadMapper.selectByParams(systemUser);
    }

    @Override
    public int deleteById(String id) {
        return systemUserWriteMapper.deleteById(id);
    }
}
