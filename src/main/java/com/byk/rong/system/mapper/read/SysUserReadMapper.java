package com.byk.rong.system.mapper.read;

import com.byk.rong.common.mapper.read.BaseReadMapper;
import com.byk.rong.system.entity.SysUser;
import org.springframework.stereotype.Repository;


@Repository
public interface SysUserReadMapper extends BaseReadMapper<SysUser>{

    SysUser selectByPrimaryKey(String id);

    /** 通过username查找用户信息 **/
    public SysUser findByUsername(String username);
}