package com.byk.rong.system.service.Impl;

import com.byk.rong.common.util.MD5Utils;
import com.byk.rong.common.util.SaltUtil;
import com.byk.rong.system.entity.SysUser;
import com.byk.rong.system.mapper.read.SysUserReadMapper;
import com.byk.rong.system.mapper.write.SysUserWriteMapper;
import com.byk.rong.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: bianyakun
 * @Date: 2018/5/31 14:08
 * @Todo:
 */
@Service
public class UserInfoServiceImpl implements UserService {


    @Autowired
    private SysUserReadMapper sysUserReadMapper;

    @Autowired
    private SysUserWriteMapper sysUserWriteMapper;

    @Override
    public int save(SysUser sysUser) {
        String password = sysUser.getPassword();
        String salt = SaltUtil.createSalt();
        String newPassword = MD5Utils.encryption(password,salt);
        sysUser.setPassword(newPassword);
        sysUser.setSalt(salt);
        return sysUserWriteMapper.insertSelective(sysUser);
    }

    @Transactional(readOnly=true)
    @Override
    public SysUser findByUsername(String username) {
        return sysUserReadMapper.findByUsername(username);
    }

    @Override
    public int insertSelective(SysUser sysUser) {
        return 0;
    }

    @Override
    public int updateSelective(SysUser sysUser) {
        return 0;
    }

    @Override
    public SysUser selectById(String id) {
        return null;
    }

    @Override
    public List<SysUser> selectByParams(SysUser sysUser) {
        return null;
    }

    @Override
    public int deleteById(String id) {
        return 0;
    }
}
