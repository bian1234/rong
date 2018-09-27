package com.byk.rong.system.service.Impl;

import com.byk.rong.system.entity.RoleMenu;
import com.byk.rong.system.mapper.read.RoleMenuReadMapper;
import com.byk.rong.system.mapper.read.RoleReadMapper;
import com.byk.rong.system.mapper.write.RoleMenuWriteMapper;
import com.byk.rong.system.mapper.write.RoleWriteMapper;
import com.byk.rong.system.service.RoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: ykbian
 * @Date: 2018/9/27 14:16
 * @Todo:
 */
@Service
public class RoleMenuServiceImpl  implements RoleMenuService {


    @Autowired
    private RoleMenuReadMapper roleMenuReadMapper;
    @Autowired
    private RoleMenuWriteMapper roleMenuWriteMapper;
    @Override
    public int insertSelective(RoleMenu roleMenu) {
        return 0;
    }

    @Override
    public int updateSelective(RoleMenu roleMenu) {
        return 0;
    }

    @Override
    public RoleMenu selectById(String id) {
        return null;
    }

    @Override
    public List<RoleMenu> selectByParams(RoleMenu roleMenu) {
        return null;
    }

    @Override
    public int deleteById(String id) {
        return 0;
    }
}
