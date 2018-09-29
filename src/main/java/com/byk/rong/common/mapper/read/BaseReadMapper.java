package com.byk.rong.common.mapper.read;

import com.byk.rong.system.entity.Menu;

import java.util.List;
import java.util.Map;

public interface BaseReadMapper<E>{

    E selectById(String id);

    List<E> selectByParams(E e);


    /**
     *  带分页查询菜单列表
     */
    List<E> list(Map<String, Object> params);

}