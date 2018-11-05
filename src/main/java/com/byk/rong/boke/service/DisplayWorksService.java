package com.byk.rong.boke.service;

import com.byk.rong.boke.entity.BokeDisplayWorks;
import com.byk.rong.common.service.BaseService;

import java.util.List;
import java.util.Map;

/**
 * @Author: ykbian
 * @Date: 2018/11/5 9:06
 * @Todo:
 */

public interface DisplayWorksService   extends BaseService<BokeDisplayWorks>{

    @Override
    int insertSelective(BokeDisplayWorks bokeDisplayWorks);

    @Override
    int updateSelective(BokeDisplayWorks bokeDisplayWorks);

    @Override
    BokeDisplayWorks selectById(String id);

    @Override
    List<BokeDisplayWorks> selectByParams(BokeDisplayWorks bokeDisplayWorks);

    @Override
    int deleteById(String id);


}
