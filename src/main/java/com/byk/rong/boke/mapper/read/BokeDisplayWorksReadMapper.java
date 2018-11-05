package com.byk.rong.boke.mapper.read;

import com.byk.rong.boke.entity.BokeDisplayWorks;
import com.byk.rong.common.mapper.read.BaseReadMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BokeDisplayWorksReadMapper   extends BaseReadMapper<BokeDisplayWorks>{
    @Override
    BokeDisplayWorks selectById(String id);

    @Override
    List<BokeDisplayWorks> selectByParams(BokeDisplayWorks bokeDisplayWorks);

    @Override
    List<BokeDisplayWorks> list(Map<String, Object> params);
}