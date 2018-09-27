package com.byk.rong.system.mapper.write;

import com.byk.rong.common.mapper.write.BaseWriteMapper;
import com.byk.rong.system.entity.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleWriteMapper extends BaseWriteMapper<Role>{

    @Override
    int insertSelective(Role role);

    @Override
    int updateSelective(Role role);

    @Override
    int deleteById(String id);
}