package com.byk.rong.system.service;

import com.byk.rong.common.service.BaseService;
import com.byk.rong.system.entity.User;

import java.util.List;

/**
 * @Author: ykbian
 * @Date: 2018/9/26 16:18
 * @Todo:
 */

public interface UserService  extends BaseService<User> {

    @Override
    int insertSelective(User user);

    @Override
    int updateSelective(User user);

    @Override
    User selectById(String id);

    @Override
    List<User> selectByParams(User user);

    @Override
    int deleteById(String id);
}
