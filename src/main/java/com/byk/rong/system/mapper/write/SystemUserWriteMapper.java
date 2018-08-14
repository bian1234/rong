package com.byk.rong.system.mapper.write;

import com.byk.rong.commen.mapper.BaseWriteMapper;
import com.byk.rong.system.entity.SystemUser;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemUserWriteMapper extends BaseWriteMapper<SystemUser>{
    @Override
    int insertSelective(SystemUser systemUser);

    @Override
    int updateSelective(SystemUser systemUser);

    @Override
    int deleteById(String id);
}