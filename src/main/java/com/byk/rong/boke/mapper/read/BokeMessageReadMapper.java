package com.byk.rong.boke.mapper.read;

import com.byk.rong.boke.entity.BokeMessage;
import org.springframework.stereotype.Repository;

@Repository
public interface BokeMessageReadMapper {
    int deleteByPrimaryKey(String id);

    int insert(BokeMessage record);

    int insertSelective(BokeMessage record);

    BokeMessage selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BokeMessage record);

    int updateByPrimaryKey(BokeMessage record);
}