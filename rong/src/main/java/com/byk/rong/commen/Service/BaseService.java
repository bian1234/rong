package com.byk.rong.commen.Service;

import java.util.List;

public interface BaseService<E> {

    int insertSelective(E e);

    int updateSelective(E e);

    E selectById(String id);

    List<E> selectByParams(E e);

    int deleteById(String id);
}
