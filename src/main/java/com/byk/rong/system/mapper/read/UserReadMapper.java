package com.byk.rong.system.mapper.read;

import com.byk.rong.common.mapper.read.BaseReadMapper;
import com.byk.rong.system.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserReadMapper   extends BaseReadMapper<User>{

    @Override
    User selectById(String id);

    @Override
    List<User> selectByParams(User user);

    User findByUsername(String userName);

}