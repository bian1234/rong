package com.byk.rong.system.service;

import com.byk.rong.common.service.BaseService;
import com.byk.rong.system.entity.UserRole;

import java.util.List;
import java.util.Map;

/**
 * @Author: ykbian
 * @Date: 2018/11/9 9:40
 * @Todo:
 *
 */

public interface UserRoleService  extends BaseService<UserRole> {

    @Override
    int insertSelective(UserRole userRoleService);

    @Override
    int updateSelective(UserRole userRoleService);

    @Override
    UserRole selectById(String id);

    @Override
    List<UserRole> selectByParams(UserRole userRoleService);

    @Override
    int deleteById(String id);

    @Override
    List<UserRole> list(Map<String, Object> params);
}
