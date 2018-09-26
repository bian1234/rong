package com.byk.rong.system.mapper.write;

import com.byk.rong.common.mapper.write.BaseWriteMapper;
import com.byk.rong.system.entity.User;
import org.springframework.stereotype.Repository;


@Repository
public interface UserWriteMapper extends BaseWriteMapper<User> {
    @Override
    int insertSelective(User user);

    @Override
    int updateSelective(User user);

    @Override
    int deleteById(String id);
}