package com.byk.rong.system.service;

import com.byk.rong.common.service.BaseService;
import com.byk.rong.system.entity.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService  extends BaseService<Role> {

    @Override
    default int insertSelective(Role role) {
        return 0;
    }

    @Override
    default int updateSelective(Role role) {
        return 0;
    }

    @Override
    default Role selectById(String id) {
        return null;
    }

    @Override
    default List<Role> selectByParams(Role role) {
        return null;
    }

    @Override
    default int deleteById(String id) {
        return 0;
    }
}
