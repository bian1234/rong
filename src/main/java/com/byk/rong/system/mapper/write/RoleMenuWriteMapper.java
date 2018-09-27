package com.byk.rong.system.mapper.write;

import com.byk.rong.common.mapper.write.BaseWriteMapper;
import com.byk.rong.system.entity.RoleMenu;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleMenuWriteMapper extends BaseWriteMapper<RoleMenu>{

    @Override
    int insertSelective(RoleMenu roleMenu);

    @Override
    int updateSelective(RoleMenu roleMenu);

    @Override
    int deleteById(String id);
}