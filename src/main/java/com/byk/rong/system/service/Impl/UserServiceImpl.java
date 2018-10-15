package com.byk.rong.system.service.Impl;

import com.byk.rong.system.entity.User;
import com.byk.rong.system.mapper.read.UserReadMapper;
import com.byk.rong.system.mapper.write.UserWriteMapper;
import com.byk.rong.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
        return userWriteMapper.updateSelective(user);
    }

    @Override
    public User selectById(String id) {
        return userReadMapper.selectById(id);
    }

    @Override
    public List<User> selectByParams(User user) {
        return userReadMapper.selectByParams(user);
    }

    @Override
    public int deleteById(String id) {
        return userWriteMapper.deleteById(id);
    }

    @Override
    public User findByUsername(String userName) {
        return userReadMapper.findByUsername(userName);
    }

    @Override
    public List<User> list(Map<String, Object> params) {
        return null;
    }
}
