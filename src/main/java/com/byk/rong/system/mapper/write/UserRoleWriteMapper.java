package com.byk.rong.system.mapper.write;

import com.byk.rong.common.mapper.write.BaseWriteMapper;
import com.byk.rong.system.entity.UserRole;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleWriteMapper extends BaseWriteMapper<UserRole>{

    @Override
    int insertSelective(UserRole userRole);

    @Override
    int updateSelective(UserRole userRole);

    @Override
    int deleteById(String id);
}