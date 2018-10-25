package com.byk.rong.system.service;

import com.byk.rong.common.service.BaseService;
import com.byk.rong.system.entity.Dept;

import java.util.List;
import java.util.Map;

/**
 * @Author: ykbian
 * @Date: 2018/10/25 9:23
 * @Todo:
 */

public interface DeptService  extends BaseService<Dept> {

    @Override
    int insertSelective(Dept dept);

    @Override
    int updateSelective(Dept dept);

    @Override
    Dept selectById(String id);

    @Override
    List<Dept> selectByParams(Dept dept);

    @Override
    int deleteById(String id);

    @Override
    List<Dept> list(Map<String, Object> params);
}
