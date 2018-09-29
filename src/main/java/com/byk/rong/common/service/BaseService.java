package com.byk.rong.common.service;

import com.byk.rong.system.entity.Menu;

import java.util.List;
import java.util.Map;

public interface BaseService<E> {

    int insertSelective(E e);

    int updateSelective(E e);

    E selectById(String id);

    List<E> selectByParams(E e);

    int deleteById(String id);
    /**
     *  带分页查询菜单列表
     */
    List<E> list(Map<String, Object> params);

}
