package com.byk.rong.system.service.Impl;

import com.byk.rong.system.entity.Dept;
import com.byk.rong.system.mapper.read.DeptReadMapper;
import com.byk.rong.system.mapper.write.DeptWriteMapper;
import com.byk.rong.system.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: ykbian
 * @Date: 2018/10/25 9:24
 * @Todo:
 */
@Service
public class DeptServiceImpl   implements DeptService {


    @Autowired
    private DeptReadMapper deptReadMapper;

    @Autowired
    private DeptWriteMapper deptWriteMapper;
    @Override
    public int insertSelective(Dept dept) {
        return deptWriteMapper.insertSelective(dept);
    }

    @Override
    public int updateSelective(Dept dept) {
        return deptWriteMapper.updateSelective(dept);
    }

    @Override
    public Dept selectById(String id) {
        return deptReadMapper.selectById(id);
    }

    @Override
    public List<Dept> selectByParams(Dept dept) {
        return null;
    }

    @Override
    public int deleteById(String id) {
        return 0;
    }

    @Override
    public List<Dept> list(Map<String, Object> params) {
        return deptReadMapper.list(params);
    }
}
