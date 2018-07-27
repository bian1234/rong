package com.byk.rong.system.service;

import com.byk.rong.commen.Service.BaseService;
import com.byk.rong.system.entity.SystemUser;

import java.util.List;

/**
 * @Author: ykbian
 * @Date: 2018/7/27 16:11
 * @Todo:
 */

public interface SystemUserService  extends BaseService<SystemUser> {

    @Override
    int insertSelective(SystemUser systemUser);

    @Override
    int updateSelective(SystemUser systemUser);

    @Override
    SystemUser selectById(String id);

    @Override
    List<SystemUser> selectByParams(SystemUser systemUser);

    @Override
    int deleteById(String id);
}
