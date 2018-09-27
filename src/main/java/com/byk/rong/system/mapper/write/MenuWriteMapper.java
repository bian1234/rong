package com.byk.rong.system.mapper.write;

import com.byk.rong.common.mapper.write.BaseWriteMapper;
import com.byk.rong.system.entity.Menu;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuWriteMapper extends BaseWriteMapper<Menu>{

    @Override
    int insertSelective(Menu menu);

    @Override
    int updateSelective(Menu menu);

    @Override
    int deleteById(String id);
}