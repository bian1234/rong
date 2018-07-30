package com.byk.rong.system.mapper.read;

import com.byk.rong.commen.mapper.BaseReadMapper;
import com.byk.rong.system.entity.SystemUser;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SystemUserReadMapper   extends BaseReadMapper<SystemUser>{
    @Override
    SystemUser selectById(String id);

    @Override
    List<SystemUser> selectByParams(SystemUser systemUser);

    SystemUser selectByUserName(String userName);
}