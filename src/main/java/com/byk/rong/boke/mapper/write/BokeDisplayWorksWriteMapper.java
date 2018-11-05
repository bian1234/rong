package com.byk.rong.boke.mapper.write;

import com.byk.rong.boke.entity.BokeDisplayWorks;
import com.byk.rong.common.mapper.write.BaseWriteMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface BokeDisplayWorksWriteMapper  extends BaseWriteMapper<BokeDisplayWorks> {
    @Override
    int insertSelective(BokeDisplayWorks bokeDisplayWorks);

    @Override
    int updateSelective(BokeDisplayWorks bokeDisplayWorks);

    @Override
    int deleteById(String id);
}