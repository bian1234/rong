package com.byk.rong.system.mapper.write;

import com.byk.rong.common.mapper.write.BaseWriteMapper;
import com.byk.rong.system.entity.Dept;
import org.springframework.stereotype.Repository;

@Repository
public interface DeptWriteMapper extends BaseWriteMapper<Dept>{
    @Override
    int insertSelective(Dept dept);

    @Override
    int updateSelective(Dept dept);

    @Override
    int deleteById(String id);
}