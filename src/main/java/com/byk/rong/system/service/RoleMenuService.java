package com.byk.rong.system.service;

import com.byk.rong.common.service.BaseService;
import com.byk.rong.system.entity.RoleMenu;

import java.util.List;

/**
 * @Author: ykbian
 * @Date: 2018/9/27 14:15
 * @Todo:
 */

public interface RoleMenuService  extends BaseService<RoleMenu> {

    @Override
    int insertSelective(RoleMenu roleMenu);

    @Override
    int updateSelective(RoleMenu roleMenu);

    @Override
    RoleMenu selectById(String id);

    @Override
    List<RoleMenu> selectByParams(RoleMenu roleMenu);

    @Override
    int deleteById(String id);
}
