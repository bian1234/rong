package com.byk.rong.common.mapper.read;

import java.util.List;

public interface BaseReadMapper<E>{

    E selectById(String id);

    List<E> selectByParams(E e);

}