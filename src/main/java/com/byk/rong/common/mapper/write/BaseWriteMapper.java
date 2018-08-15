package com.byk.rong.common.mapper.write;


public interface BaseWriteMapper<E> {

    int insertSelective(E e);

    int updateSelective(E e);

    int deleteById(String id);

}