package com.byk.rong.system.mapper.write;

import com.byk.rong.system.entity.SysUser;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserWriteMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
}