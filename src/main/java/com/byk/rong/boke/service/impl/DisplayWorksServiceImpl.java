package com.byk.rong.boke.service.impl;

import com.byk.rong.boke.entity.BokeDisplayWorks;
import com.byk.rong.boke.mapper.read.BokeDisplayWorksReadMapper;
import com.byk.rong.boke.mapper.write.BokeDisplayWorksWriteMapper;
import com.byk.rong.boke.service.DisplayWorksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: ykbian
 * @Date: 2018/11/5 9:07
 * @Todo:
 */
@Service
public class DisplayWorksServiceImpl   implements DisplayWorksService {


    @Autowired
    private BokeDisplayWorksReadMapper bokeDisplayWorksReadMapper;

    @Autowired
    private BokeDisplayWorksWriteMapper bokeDisplayWorksWriteMapper;


    @Override
    public int insertSelective(BokeDisplayWorks bokeDisplayWorks) {
        return bokeDisplayWorksWriteMapper.insertSelective(bokeDisplayWorks);
    }

    @Override
    public int updateSelective(BokeDisplayWorks bokeDisplayWorks) {
        return bokeDisplayWorksWriteMapper.updateSelective(bokeDisplayWorks);
    }

    @Override
    public BokeDisplayWorks selectById(String id) {
        return bokeDisplayWorksReadMapper.selectById(id);
    }

    @Override
    public List<BokeDisplayWorks> selectByParams(BokeDisplayWorks bokeDisplayWorks) {
        return bokeDisplayWorksReadMapper.selectByParams(bokeDisplayWorks);
    }

    @Override
    public int deleteById(String id) {
        return 0;
    }

    @Override
    public List<BokeDisplayWorks> list(Map<String, Object> params) {
        return null;
    }
}
