package com.byk.rong.boke.mapper.read;

import com.byk.rong.boke.entity.BokeDisplayWorks;
import org.springframework.stereotype.Repository;

@Repository
public interface BokeDisplayWorksReadMapper {
    int deleteByPrimaryKey(String id);

    int insert(BokeDisplayWorks record);

    int insertSelective(BokeDisplayWorks record);

    BokeDisplayWorks selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BokeDisplayWorks record);

    int updateByPrimaryKey(BokeDisplayWorks record);
}