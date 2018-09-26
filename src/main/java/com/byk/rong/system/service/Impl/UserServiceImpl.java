package com.byk.rong.system.service.Impl;

import com.byk.rong.system.entity.User;
import com.byk.rong.system.mapper.read.UserReadMapper;
import com.byk.rong.system.mapper.write.UserWriteMapper;
import com.byk.rong.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: ykbian
 * @Date: 2018/9/26 16:19
 * @Todo:
 */
@Service
public class UserServiceImpl  implements UserService {

    @Autowired
    private UserReadMapper userReadMapper;
    @Autowired
    private UserWriteMapper userWriteMapper;

    @Override
    public int insertSelective(User user) {
        return userWriteMapper.insertSelective(user);
    }

    @Override
    public int updateSelective(User user) {
        return 0;
    }

    @Override
    public User selectById(String id) {
        return null;
    }

    @Override
    public List<User> selectByParams(User user) {
        return userReadMapper.selectByParams(user);
    }

    @Override
    public int deleteById(String id) {
        return 0;
    }
}
