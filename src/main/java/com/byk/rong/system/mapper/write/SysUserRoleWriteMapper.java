package com.byk.rong.system.mapper.write;

import com.byk.rong.system.entity.SysUserRole;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserRoleWriteMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysUserRole record);

    int insertSelective(SysUserRole record);


    int updateByPrimaryKeySelective(SysUserRole record);

    int updateByPrimaryKey(SysUserRole record);
}