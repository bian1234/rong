package com.byk.rong.system.service;

import com.byk.rong.common.service.BaseService;
import com.byk.rong.system.entity.UserRole;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface UserRoleService  extends BaseService<UserRole> {

    @Override
    default int insertSelective(UserRole userRole) {
        return 0;
    }

    @Override
    default int updateSelective(UserRole userRole) {
        return 0;
    }

    @Override
    default UserRole selectById(String id) {
        return null;
    }

    @Override
    default List<UserRole> selectByParams(UserRole userRole) {
        return null;
    }

    @Override
    default int deleteById(String id) {
        return 0;
    }
}
