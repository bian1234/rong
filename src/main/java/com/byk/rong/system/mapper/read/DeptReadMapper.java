package com.byk.rong.system.mapper.read;

import com.byk.rong.common.mapper.read.BaseReadMapper;
import com.byk.rong.system.entity.Dept;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DeptReadMapper extends BaseReadMapper<Dept>{
    @Override
    Dept selectById(String id);

    @Override
    List<Dept> selectByParams(Dept dept);

    @Override
    List<Dept> list(Map<String, Object> params);
}